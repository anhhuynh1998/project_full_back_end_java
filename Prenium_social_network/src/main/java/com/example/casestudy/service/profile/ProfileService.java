package com.example.casestudy.service.profile;

import com.example.casestudy.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@AllArgsConstructor
public class ProfileService {
    private final UserProfileRepository userProfileRepository;

//    public String getInfomation(Model model) {
////        User
//    }
}
