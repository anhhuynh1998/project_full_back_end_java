package com.example.casestudy.model;

import com.example.casestudy.model.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String phoneNumber;

    private String password;
    @Enumerated(EnumType.STRING)

    private ERole role;
}
