
function like(postId, likeCount){
    var heartImage = document.querySelector(`.heart${postId}`);
    var heartColorImage = document.querySelector(`.heart-color${postId}`);
    var likeCountElement = document.getElementById(`likeCount${postId}`);

    // Kiểm tra nếu thẻ heart đang hiển thị thì ẩn nó và hiển thị thẻ heart-color
    if (heartImage.style.display === 'inline-block') {
        heartImage.style.display = 'none';
        heartColorImage.style.display = 'inline-block';
        likeCount++
    } else {
        // Ngược lại, nếu thẻ heart-color đang hiển thị thì ẩn nó và hiển thị thẻ heart
        heartImage.style.display = 'inline-block';
        heartColorImage.style.display = 'none';

    }
    likeCount = Math.max(0, likeCount);
    likeCountElement.textContent = `${likeCount} like`;
    $.ajax({
        url: "http://localhost:8080/api/likes?idPost=" + postId,
        type: "GET",
    })
}