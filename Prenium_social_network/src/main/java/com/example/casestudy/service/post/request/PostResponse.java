package com.example.casestudy.service.post.request;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Content;
import com.example.casestudy.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {
    private Long id;
    private User user;
    private Content content;
    private LocalDateTime create_date;
    private int likeCount;

    private List<Comment> comments;
    private boolean like;
}
