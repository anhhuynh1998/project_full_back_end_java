const reply = document.querySelector('.share-box-inner')
function showReply(id){
    const reply = document.getElementById('reply-comment-'+id);
    if(reply.style.display === 'none'){
        reply.style.display = 'block'
        document.getElementById("show-hide-text-"+id).innerText = " _Hide_reply"
    }else {
        reply.style.display = 'none'
        document.getElementById("show-hide-text-"+id).innerText = " _Show_reply"
    }
}

function submitReply(id){
    // lấy user
    // const userForm = userName.value
    // lấy bình luận
    const reply = document.getElementById('commentReply'+id)
    const value = reply.value
    // nếu nhập vào là rỗng
    if(value !== ''){
        $.ajax({
            url: '/api/replies',
            type: 'POST',
            data: JSON.stringify({
                content: value,
                id: id
            }),
            contentType: 'application/json',
            success: function (response) {
                const divPar = document.getElementById("reply-comment-"+id);
                const divNew = document.createElement('div')
                divNew.classList ="share-box-inner"
                let timeReply = timeNow(response.reply_date);
                // 'comment-container'
                divNew.innerHTML += `  <div class="profile-thumb">
                                                <a href="#">
                                                    <figure class="profile-thumb-middle" >
                                                        <img  class="new-photo" id="userReply" src="assets/images/photos/photo1.jpg"/>
                                                    </figure>
                                                </a>
                                            </div>
                                            <!-- profile picture end -->
                                            <div class="share-content-box w-100">
                                                <div class="comment-info" id="commentSection">
                                                            <h6>${response.user.fullName}</h6>
                                                            
                                                            <p  class="comment" >${response.text}</p>
                                                            
                                                         
                                                            <span class="time-comment">${timeReply}</span>
                                                </div>
                                            </div>
                                            `
                divPar.insertAdjacentElement('afterbegin', divNew);
                reply.value = ""
                reply.focus()

            },
            //thêm mới vào div
            error: function () {
                alert('Không thể thêm bình luận!');
            }
        });
    }
}