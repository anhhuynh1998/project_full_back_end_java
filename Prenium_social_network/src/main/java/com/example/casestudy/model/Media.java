package com.example.casestudy.model;

import com.example.casestudy.model.enums.FileType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @Enumerated(EnumType.STRING)
    private FileType fileType;
    @ManyToOne
    @JoinColumn(name = "id_content")
    @JsonIgnore

    private Content content;

    @ManyToOne
    @JoinColumn(name = "id_contentComment")
    @JsonIgnore
    private ContentComment contentComment;


}
