package com.example.casestudy.service.like;

import com.example.casestudy.model.Like;
import com.example.casestudy.model.Post;
import com.example.casestudy.model.User;
import com.example.casestudy.model.enums.Status;
import com.example.casestudy.repository.LikeRepository;
import com.example.casestudy.repository.PostRepository;
import com.example.casestudy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    public void pressLikeButton(Long idPost, UserDetails userDetails){
        String userName = userDetails.getUsername();
        User user = userRepository.findByEmailOrUserNameOrPhoneNumber(userName,userName,userName);
        Post post = postRepository.findById(idPost).get();
        Like like = likeRepository.findByUserAndPost(user,post);
        if (like == null){
            like = new Like();
            like.setPost(post);
            like.setUser(user);
            like.setStatus(Status.LIKE);
            likeRepository.save(like);
        } else {
            if (like.getStatus().equals(Status.LIKE)){
                like.setStatus(Status.UN_LIKE);
            } else {
                like.setStatus(Status.LIKE);
            }
            likeRepository.save(like);
        }
    }
    public List<Like> getListLikeByUser(User user){
        return likeRepository.findAllByUser(user);
    }
    public Like getPostLikeByUser(User user, Post post){
        return likeRepository.findByUserAndPost(user,post);
    }
    public int countLike(Post post){
        return likeRepository.countLikeByPost(post);
    }
}
