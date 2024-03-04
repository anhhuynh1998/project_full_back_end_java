package com.example.casestudy.repository;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//@Query("select Comment from Comment where Comment.post.id = : postId")
//
//    List<Comment> getCommentByPostId( Long postId);
    List<Comment> findByPostContaining(Post post);

    List<Comment> findByPostId(Long postId);

    Optional<Comment> findById(Long id);
}
