package com.example.casestudy.service.auth.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {

    private String fullName;

    private String email;

    private String userName;

    private String phoneNumber;

    private String password;

}
