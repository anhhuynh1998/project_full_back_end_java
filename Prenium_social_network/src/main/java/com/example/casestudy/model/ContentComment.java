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
public class ContentComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "contentComment", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_content_conmment")
    @JsonIgnore
    private Comment comment;

    @Column(columnDefinition = ("LONGTEXT"))
    private String text;

    @OneToMany(mappedBy = "contentComment", cascade = CascadeType.ALL)
    private List<Media> media;
}
