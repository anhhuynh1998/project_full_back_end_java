
const commentsCont = document.querySelector('#comment-container')
const submit = document.querySelector('#comment-submit')
let API_COMMENT = 'http://localhost:8080/api/comments';
function showComment(id) {
    const comment = document.getElementById('comment-'+id);
    if(comment.style.display === 'none'){
        comment.style.display = 'block'
    }else {
        comment.style.display = 'none'
    }
}

// Đoạn mã JavaScript
function submitComment(id) {
    let time = timeNow(id.comment_date)
    // lấy user
    // const userForm = userName.value
    // lấy bình luận
    const comment = document.querySelector('#comment' + id)
    const value = comment.value
    // nếu nhập vào là rỗng
    if (value !== '') {
        $.ajax({
            url: '/api/comments',
            type: 'POST',
            data: JSON.stringify({
                content: value,
                id: id
            }),
            contentType: 'application/json',
            success: function (response) {
                console.log(response)
                // Đã thêm mới bình luận thành công, cập nhật lại danh sách bình luận
                // getCommentsForPost(id);

                // Xóa nội dung trong textarea sau khi thêm bình luận thành công
                let timeComment = timeNow(response.comment_date);
                document.querySelector('#comment-' + id).style.display = 'block'
                document.querySelector('#comment' + id).value = ''

                const divNew = document.createElement('div')
                divNew.classList = "form-comment"

                divNew.id = `form-comment-${response.id}`;
                // 'comment-container'
                divNew.innerHTML += ` <div class="profile-thumb">
                                    <a href="#">
                                        <figure class="profile-thumb-middle" >
                                            <img  class="new-photo" src="assets/images/photos/photo1.jpg"/>
                                        </figure>
                                    </a>
                                </div>
                                <!-- profile picture end -->
                                 <div class="comment-info " id="commentSection${response.id}">
                                    <h6>${response.user.fullName}</h6>
                                    <p  class="comment" >${response.contentComment.text}</p>
                                     <button onclick="showReply(${response.id})">
                                        <span class="bi bi-reply" >Reply</span>
                                    </button>
                                    <span class="time-comment">${timeComment}</span>
                                   <div class="comment-edit">
                                    <textarea class="edit-textarea"  >${response.contentComment.text}</textarea>
                                    <div>
                                    <button type="button" class="btn-share" onclick="submitEditComment(${response.id})">
                                    <i class="fa-solid fa-paper-plane"></i>
                                    </button>
                                    <button type="button" onclick="cancelReply(${response.id})">Hủy</button>
                                    </div>
                                    </div>
                                </div>
                                                       <div class="comment-icon">
                                            <i class="fa-solid fa-ellipsis"></i>                           
                                           
                                            <div class="comment-reply-action">
                                             <div class="buton-editer">
                                                     <button type="button" onclick="editCommentFromPost(${response.id})">Chỉnh sửa</button>
                                             </div>   
                                            <div class="buton-editer">
                                                     <button type="button" onclick="removeCommentFromPost(${response.id})">Xóa</button>
                                              </div>  
                                        </div>
                                     </div>  
                                    <div class="reply-comment" id="reply-comment-${response.id}">
                                        <div class="share-box-inner">
                                            <!-- profile picture end -->
                                            <div class="profile-thumb">
                                                <a href="#">
                                                    <figure class="profile-thumb-middle" >
                                                        <img  class="new-photo" id="userReply" src="assets/images/photos/photo1.jpg"/>
                                                    </figure>
                                                </a>
                                            </div>
                                            <!-- profile picture end -->
                          
                                            <div class="share-content-box w-100">
                                                <form class="share-text-box">
                                                    <textarea name="share" id="commentReply" class="share-text-field" placeholder="Say Something"></textarea>
                                                    <button type="submit" class="btn-share" id="submit">Sent</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                            `
                document.querySelector('#comment-' + id).insertAdjacentElement('afterbegin', divNew);
                let countComment = +document.getElementById("countComment").textContent;
                document.getElementById("countComment").innerText = countComment + 1;
                checkUserAccess(response.id, currentUser);
            }, //thêm mới vào div
            error: function () {
                alert('Không thể thêm bình luận!');
            }
        });
    }
}
function checkUserAccess(commentId, currentUser) {
    const commentActions = document.querySelector(`#commentSection${commentId} .comment-reply-action`);

    if (currentUser && currentUser.id === commentId) {
        commentActions.style.display = 'block';
    } else {
        commentActions.style.display = 'none';
    }
}



function editCommentFromPost(id) {
    const commentDiv = document.getElementById(`commentSection${id}`);
    const commentTextElement = commentDiv.querySelector(".comment");
    const commentEditElement = commentDiv.querySelector(".comment-edit");
    const commentTextareaElement = commentDiv.querySelector(`.edit-textarea`);

    commentTextElement.style.display = "none"
    commentEditElement.style.display = "flex"
    commentTextareaElement.focus();

}

function cancelReply(id) {
    const commentDiv = document.getElementById(`commentSection${id}`);
    const commentTextElement = commentDiv.querySelector(".comment");
    const commentEditElement = commentDiv.querySelector(".comment-edit");


    commentTextElement.style.display = "block"
    commentEditElement.style.display = "none"

}


function submitEditComment(id) {
    const commentDiv = document.getElementById(`commentSection${id}`);
    const commentTextElement = commentDiv.querySelector(".comment");
    const commentEditElement = commentDiv.querySelector(".comment-edit");
    const commentTextareaElement = commentDiv.querySelector(`.edit-textarea`);
    const textarea =commentTextareaElement.value
    if (textarea !== '') {
        $.ajax({
            url: API_COMMENT + '/' + id,
            type: 'PUT',
            data: JSON.stringify({
                content: textarea, id: id
            }), contentType: 'application/json',
            success: function (response) {
                console.log(response)
                commentTextElement.style.display = "block"
                commentEditElement.style.display = "none"
                commentTextElement.innerText = textarea;
            },
            error: function () {
                alert('Không thể thêm bình luận!');
            }
        });
    }
}
function deleteCommentById(id) {

    $.ajax({
        url: API_COMMENT + '/' + id,
        type: 'DELETE',
    }).done(response => {
        const container = document.getElementById("comment-"+response.post().id);
        const div =document.getElementById("form-comment-"+ id);

        if (div) {
            container.removeChild(div);
        }
        let countComment = +document.getElementById("countComment").textContent;
        document.getElementById("countComment").innerText = countComment - 1;
    })
}

function removeCommentFromPost(id) {
    if (confirm("Are you sure you want to delete this comment?")) {
        deleteCommentById(id);
    }
}
