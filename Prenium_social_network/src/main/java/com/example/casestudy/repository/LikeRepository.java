package com.example.casestudy.repository;

import com.example.casestudy.model.Like;
import com.example.casestudy.model.Post;
import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    public Like findByUserAndPost(User user,Post post);
    public int countLikeByPost(Post post);
    List<Like> findAllByUser(User user);
}
