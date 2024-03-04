package com.example.casestudy.service.comment;

import com.example.casestudy.model.*;
import com.example.casestudy.repository.*;
import com.example.casestudy.service.comment.request.CommentSaveRequest;
import com.example.casestudy.service.user.UserService;
import com.example.casestudy.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ContentCommentRepo contentCommentRepo;

    public Comment saveComment(CommentSaveRequest commentSaveRequest, User user){
        Comment comment = new Comment();
        comment.setUser(user);

        Post post = postRepository.findById(Long.valueOf(commentSaveRequest.getId())).get();
        comment.setPost(post);

        ContentComment content = new ContentComment();
        content.setText(commentSaveRequest.getContent());
        contentCommentRepo.save(content);

        comment.setContentComment(content);
        comment.setComment_date(LocalDateTime.now());

        return commentRepository.save(comment);
    }
    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }
    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
    }
    public List<Comment> getCommentByPostId(Long id){
//        Post post = postRepository.findById(id).get();
        return commentRepository.findByPostId(id);
    }
    public Comment update(CommentSaveRequest request,Long id){
        Comment comment = commentRepository.findById(id).get();
        comment.setComment_date(LocalDateTime.now());
       ContentComment contentComment = comment.getContentComment();
       contentComment.setText(request.getContent());
        comment.setContentComment(contentComment);
       return commentRepository.save(comment);
    }

    public Comment delete(Long id) {
        Comment comment =commentRepository.findById(id).get();
        commentRepository.deleteById(id);
        return comment;
    }
}
