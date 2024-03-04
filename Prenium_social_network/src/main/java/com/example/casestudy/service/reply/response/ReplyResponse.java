package com.example.casestudy.service.reply.response;

import com.example.casestudy.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponse {
    private Long id;
    private Comment comment;
}
