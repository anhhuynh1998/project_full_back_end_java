function getUserLogIn() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "http://localhost:8080/api/user",
            type: "GET",
            headers: {
                'Accept': 'application/json',
            },
            processData: false,
            contentType: false,
            success: function(response) {
                resolve(response);
            },
            error: function(error) {
                reject(error);
            }
        });
    });
}

// await getUserLogIn()




function showPost() {
    const post = document.getElementById('textbox');
    if(post.style.display === 'block'){
        post.style.display = 'none'
    }else {
        post.style.display = 'block'
    }
}
function uploadFile() {
    const fileInput = document.getElementById("fileInput");
    const allowedTypes = ["image/jpeg", "image/png", "image/gif", "video/mp4", "video/quicktime"];
    const files = fileInput.files;

    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const fileType = file.type;

        if (!allowedTypes.includes(fileType)) {
            alert("Chỉ chọn các tệp ảnh (JPEG, PNG, GIF) hoặc video (MP4, QuickTime).");
            fileInput.value = ""; // Xóa tệp không hợp lệ khỏi ô chọn tệp
            return false;
        }

        const reader = new FileReader();

        reader.addEventListener("load", function () {
            const imageURL = reader.result;
            const img = document.createElement("img");
            img.src = imageURL;
            img.style.maxWidth = "100%";
            img.style.maxHeight = "300px"; // Tùy chỉnh kích thước hiển thị ảnh
            const imagePreview = document.getElementById("imagePreview");
            imagePreview.innerHTML = ""; // Xóa nội dung cũ trước khi thêm ảnh mới
            imagePreview.appendChild(img);
        });

        reader.readAsDataURL(file);
    }

    return true;
}
function createPost(e){
    e.preventDefault();
    const form = document.getElementById("postForm");
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());
    console.log(data);
    $.ajax({
        url: "http://localhost:8080/api/posts/create",
        type: "POST",
        headers: {
            'Accept': 'application/json',
        },
        processData: false,
        contentType: false,
        data: formData,
        success: function (response) {
            let postBlock = document.getElementById('post-block')

            console.log("response", response)
            postBlock.insertAdjacentHTML("afterbegin", renderData1(response));
        },
        error: function (error) {
            console.log(error);
        }
    });

}


function handleRenderDatas() {
    $.ajax({
        type: "get",
        dataType: 'json',
        url: "http://localhost:8080/api/posts",
        success: function (datas) {
            if(datas){
                datas.reverse();
            }
            console.log(datas)
            renderDatas(datas)
        },
        error: function (data, textStatus, errorThrown) {
            console.log("Data")
            /* $('#message').html('Email đã tồn tại'); */
        },
    });
}
handleRenderDatas()
 function renderDatas(datas) {
    let html = ``;


    datas.forEach((data) => {
        html += renderData1(data)
        // renderComment(data.comments,data.id);
    })
    let postBlock = document.getElementById('post-block')
    postBlock.innerHTML = html;
}

 function renderData1(data) {


    let time = timeNow(data.create_date)
    let countComment = data.comments ? data.comments.length : 0
    let mediaHtml = ``;
    let commentBlock = ``;


    let heartIcon = `<img class="heart${data.id}" src="assets/images/icons/heart.png" alt="" style="display: inline-block">`;
    let revHeart = `<img class="heart-color${data.id}" src="assets/images/icons/heart-color.png" alt="" style="display: none">`;
    if (data.like === true){
        heartIcon = `<img class="heart-color${data.id}" src="assets/images/icons/heart-color.png" alt="" style="display: inline-block">`;
        revHeart = `<img class="heart${data.id}" src="assets/images/icons/heart.png" alt="" style="display: none">`;
        }

    if(data.content.media !== null){
    data.content.media.forEach((media) => {
        mediaHtml += `<img src=${media.url} alt="post image">`;
    });
    }
    if(data.comments && data.comments.length > 0){
        data.comments.reverse().forEach(comment => {
            let replyE = ``;
            let bockUserEdit = ``;

            console.log(comment)
            if (userLogin.id == comment.user.id){
                bockUserEdit =`
                          <div class="comment-icon">
                                            <i class="fa-solid fa-ellipsis"></i>

                                            <div class="comment-reply-action">
                                             <div class=" buton-editer">
                                                     <button type="button" onclick="editCommentFromPost(${comment.id})"  id="editBtn-${comment.id}">Chỉnh sửa </button>
                                             </div>
                                            <div class=" buton-editer">
                                                     <button type="button" onclick="removeCommentFromPost(${comment.id})" id="deleteBtn-${comment.id}">Xóa</button>
                                              </div>
                                        </div>
                                     </div>
                `
            }
            if(comment.listReply && comment.listReply.length >0){
                comment.listReply.reverse().forEach( reply => {
                    let timeRep = timeNow(reply.reply_date);
                    replyE += `
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
                                                <div class="comment-info" id="commentSection">
                                                            <h6>${reply.user.fullName}</h6>
                                                            <p  class="comment" >${reply.text}</p>
                                                     <span class="time-comment">${timeRep}</span>
                                                </div>
                                            </div>
                                        </div>`
                })
            }
            let timeComment = timeNow(comment.comment_date);
            commentBlock += `
                     <div class="form-comment" >
                                <!-- profile picture end -->
                                <div class="profile-thumb">
                                    <a href="#">
                                        <figure class="profile-thumb-middle" >
                                            <img  class="new-photo" src="assets/images/photos/photo1.jpg"/>
                                        </figure> 
                                    </a>
                                </div>
                                <!-- profile picture end -->

                            <div class="comment-info " id="commentSection${comment.id}">
                                    <h6>${comment.user.fullName}</h6>
                                    <p  class="comment" >${comment.contentComment.text}</p>
                                   <div class="comment-edit">
                                    <textarea class="edit-textarea"  >${comment.contentComment.text}</textarea>
                                    <div>
                                    <button type="button" class="btn-share" onclick="submitEditComment(${comment.id})">
                                    <i class="fa-solid fa-paper-plane"></i>
                                    </button>
                                    <button type="button" onclick="cancelReply(${comment.id})" ">Hủy</button>
</div>
</div>
                                    <button onclick="showReply(${comment.id})">
                                        <span class="bi bi-reply" >Reply</span>
                                    </button>
                                    <span class="time-comment">${timeComment}</span>
                              </div> 
                              ${bockUserEdit}
                            <div class="replys-comment" id="reply-comment-${comment.id}">
                                    
                                     ${replyE}
                                        
                                        <div class="share-box-inner">
                                                 <div class="profile-thumb">
                                                      <a href="#">
                                                                <figure class="profile-thumb-middle" >
                                                                    <img  class="new-photo"  src="assets/images/photos/photo1.jpg"/>
                                                                </figure>
                                                      </a>
                                                 </div>
                                                 <div class="share-text-box" id="commentForm">
                                                       <textarea name="share" id="commentReply${comment.id}" class="share-text-field" placeholder="Say Something"></textarea>
                                                       <button type="submit" class="btn-share" id="submit" onclick="submitReply(${comment.id})">SEND</button>
                                                 </div>
                                        </div>
                                    </div>
                                  
                                        
                    </div>`
        });
    }
    return `<div class="card">
                    <!-- post title start -->
                    <div class="post-title d-flex align-items-center">
                        <!-- profile picture end -->
                        
                        <div class="profile-thumb">
                            <a>
                                <figure class="profile-thumb-middle" >
                                    <img  class="new-photo" src="assets/images/photos/photo2.jpg" alt="profile picture">
                                </figure>
                            </a>
                        </div>
                        <!-- profile picture end -->

                        <div class="posted-author">
                            <h6 class="author"><a href="/profile">${data.user.fullName}</a></h6>
                            <span class="post-time">${time}</span>
                        </div>

                        <div class="post-settings-bar">
                            <span></span>
                            <span></span>
                            <span></span>
                            <div class="post-settings arrow-shape">
                                <ul>
                                    <li><button>copy link to adda</button></li>
                                    <li><button>edit post</button></li>
                                    <li><button>embed adda</button></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- post title start -->

                    <div class="post-content">
                        <p class="post-desc">
                            ${data.content.text}
                        </p>
                        <div class="post-thumb-gallery">
                            <figure class="post-thumb img-popup">
                                <a>
                                    ${mediaHtml}
                                </a>
                            </figure>
                        </div>
                        <div class="post-meta">
                            <button class="post-meta-like" onclick="like(${data.id},${data.likeCount})">
                                ${heartIcon}
                                ${revHeart}
                                <span id="likeCount${data.id}">${data.likeCount} like</span>
                                <strong>201</strong>
                            </button>
                            <ul class="comment-share-meta">
                                <li onclick="showComment(${data.id})">
                      <button class="post-comment" >
                                        <i class="bi bi-chat-bubble" ></i>
                                        <span id="countComment">${countComment}</span>
                                    </button>
                                </li>
                                <li>
                                    <button class="post-share">
                                        <i class="bi bi-share"></i>
                                        <span>0</span>
                                    </button>
                                </li>
                            </ul>
                        </div>

                        <div class="share-box-inner">
                            <!-- profile picture end -->
                            <div class="profile-thumb">
                                <a href="#">
                                    <figure class="profile-thumb-middle" >
                                        <img  class="new-photo" id="user" src="assets/images/photos/photo1.jpg"/>
                                    </figure>
                                </a>
                            </div>
                            <!-- profile picture end -->

                            <div class="share-content-box w-100">
                                <form class="share-text-box" id="commentForm">
                                    <textarea name="content" id="comment${data.id}" class="share-text-field" placeholder="Say Something"></textarea>
                                    <a type="submit" class="btn-share" onclick="submitComment('${data.id}')" >SEND</a>
                                </form>
                            </div>
                        </div>

                        <div class="comment-container" id="comment-${data.id}" >
                            ${commentBlock}
                        </div>
                    </div>
                 </div>
           </div>`
}

function timeNow(date) {
// Tạo đối tượng Date từ chuỗi thời gian cụ thể
    let pastDate = new Date(date);

// Tính toán khoảng thời gian giữa thời điểm cụ thể và thời điểm hiện tại (tính bằng mili giây)
    let elapsed = new Date() - pastDate;

// Đổi đơn vị thời gian sang phút và làm tròn về số nguyên
    let elapsedMinutes = Math.round(elapsed / (1000 * 60));

// Hiển thị thời gian đã trôi qua theo đơn vị phù hợp
    if (elapsedMinutes == 0) {
        return  "Vừa xong";
    }
    else if (elapsedMinutes < 60) {
        return elapsedMinutes + " phút trước";
    } else if (elapsedMinutes < 24 * 60) {
        let elapsedHours = Math.round(elapsedMinutes / 60);
        return elapsedHours + " giờ trước";
    } else {
        let elapsedDays = Math.round(elapsedMinutes / (60 * 24));
        return elapsedDays + " ngày trước";
    }
}