package com.example.casestudy.controller;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Post;
import com.example.casestudy.service.comment.CommentService;
import com.example.casestudy.service.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Controller

public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    @GetMapping("/{postId}")
    public String showPostDetail(@PathVariable Long postId, Model model) {
        Post post = postService.getPostById(postId).orElse(null);
        if (post != null) {
            model.addAttribute("post", post);
            model.addAttribute("postId", postId); // Thêm postId vào Model
            model.addAttribute("comments", commentService.getCommentByPostId(postId));
        }
        return "Long";
    }

}
