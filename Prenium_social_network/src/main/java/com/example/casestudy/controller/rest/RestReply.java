package com.example.casestudy.controller.rest;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Reply;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.CommentRepository;
import com.example.casestudy.repository.ReplyRepository;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.comment.CommentService;
import com.example.casestudy.service.reply.ReplyService;
import com.example.casestudy.service.reply.request.ReplySaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/replies")
@AllArgsConstructor
public class RestReply {
    private final ReplyService replyService;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @PostMapping
    public Reply saveReply(@RequestBody ReplySaveRequest replySaveRequest , Authentication authentication ){
        User user = userRepository.findByUserName(authentication.getName());

        return replyService.saveReply(replySaveRequest,user );
    }
}
