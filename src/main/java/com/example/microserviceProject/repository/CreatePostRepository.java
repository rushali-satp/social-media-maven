package com.example.microserviceProject.repository;

import com.example.microserviceProject.entity.CreatePost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreatePostRepository extends JpaRepository<CreatePost, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE CreatePost p SET p.likes = :likeCount WHERE p.postId = :postId")
    void updateLikeCount(@Param("postId") Integer postId,
                         @Param("likeCount") Integer likeCount);


    @Transactional
    @Query("select a from CreatePost a WHERE a.userId = :userId and a.isActive = true")
    List<CreatePost> getProfilePostDetails(@Param("userId") Integer userId);

    List<CreatePost> findByIsActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE CreatePost p SET p.isActive = false WHERE p.postId = :postId")
    int softDeletePost(@Param("postId") Integer postId);
}
