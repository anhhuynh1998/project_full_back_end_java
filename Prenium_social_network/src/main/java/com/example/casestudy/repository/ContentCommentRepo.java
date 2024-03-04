package com.example.casestudy.repository;

import com.example.casestudy.model.ContentComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentCommentRepo extends JpaRepository<ContentComment, Long> {
}
