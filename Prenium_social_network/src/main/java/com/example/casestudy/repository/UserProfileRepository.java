package com.example.casestudy.repository;

import com.example.casestudy.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
