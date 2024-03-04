package com.example.casestudy.controller.rest;

import com.example.casestudy.model.Like;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.like.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@AllArgsConstructor
public class LikeRestController {
    private final UserRepository userRepository;
    private final LikeService likeService;

   @GetMapping
    public void pressLikeButton(@RequestParam("idPost") long idPost, Authentication authentication){
       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
       likeService.pressLikeButton(idPost,userDetails);
   }
}
