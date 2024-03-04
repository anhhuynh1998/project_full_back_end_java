package com.example.casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "content", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_post")
    @JsonIgnore
    private Post post;

    @Column(columnDefinition = ("LONGTEXT"))
    private String text;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<Media> media;
}
