package com.example.casestudy.controller.rest;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Reply;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.CommentRepository;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.comment.CommentService;
import com.example.casestudy.service.comment.request.CommentSaveRequest;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class RestComment {
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final UserRepository userRepository;

    @PostMapping
    public Comment saveComment(@RequestBody CommentSaveRequest commentSaveRequest, Authentication authentication) {
        User user = userRepository.findByUserName(authentication.getName());

        return commentService.saveComment(commentSaveRequest, user);

    }

    @GetMapping
    public List<Comment> getAllComment(@RequestParam("postId") Long postId) {
        List<Comment> comments = commentService.getCommentByPostId(postId);
        return comments;
    }


    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody CommentSaveRequest request, @PathVariable Long id) {
        Comment comment = commentService.update(request, id);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Comment comment = commentService.delete(id);
        return ResponseEntity.ok(comment.getPost());
    }
}
