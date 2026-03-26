package com.example.microserviceProject.repository;

import com.example.microserviceProject.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikes, Integer> {

    boolean existsByPostIdAndUserId(Integer postId, Integer userId);

    @Query("SELECT COUNT(pl) FROM PostLikes pl WHERE pl.postId = :postId")
    Integer countLikesByPostId(@Param("postId") Integer postId);


}
