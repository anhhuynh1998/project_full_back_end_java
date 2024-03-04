package com.example.casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_comment")
    @JsonIgnore
    private Comment comment;

    private LocalDateTime reply_date;

    @Column(columnDefinition = ("LONGTEXT"))
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user")
    private User user;

}
