package com.example.casestudy.service.reply;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Reply;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.CommentRepository;
import com.example.casestudy.repository.ReplyRepository;
import com.example.casestudy.service.comment.CommentService;
import com.example.casestudy.service.reply.request.ReplySaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReplyService {
    private CommentRepository commentRepository;
    private ReplyRepository replyRepository;


    public Reply saveReply(ReplySaveRequest replySaveRequest , User user){
        Reply reply = new Reply();

        Comment commentReply = commentRepository.findById(Long.valueOf(replySaveRequest.getId())).get();

        reply.setComment(commentReply);
        reply.setText(replySaveRequest.getContent());
        reply.setReply_date(LocalDateTime.now());
        reply.setUser(user);

        return replyRepository.save(reply);
    }
}
