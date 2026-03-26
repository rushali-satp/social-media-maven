package com.example.microserviceProject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile_details", schema = "public")
public class ProfileDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer postId;

    @Column(name = "user_login_id", nullable = false)
    private String userLoginId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "bio_details", nullable = false, length = 500)
    private String bioDetails;

    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @Column(name = "profile_photo", nullable = false)
    private String profileImageName;

    @Column(name = "profile_photo_path", nullable = false)
    private String profileImagePath;

    @Column(name = "profile_image_size", nullable = false)
    private String profileImageSize;

    @Column(name = "total_post")
    private Integer totalPost;

    @Column(name = "total_followers")
    private Integer totalFollowers;

    @Column(name = "total_following")
    private Integer totalFollowing;

    @PrePersist
    protected void onCreate() {
        if (this.createdOn == null) {
            this.createdOn = LocalDateTime.now();
        }
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBioDetails() {
        return bioDetails;
    }

    public void setBioDetails(String bioDetails) {
        this.bioDetails = bioDetails;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getProfileImageName() {
        return profileImageName;
    }

    public void setProfileImageName(String profileImageName) {
        this.profileImageName = profileImageName;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getProfileImageSize() {
        return profileImageSize;
    }

    public void setProfileImageSize(String profileImageSize) {
        this.profileImageSize = profileImageSize;
    }

    public Integer getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(Integer totalPost) {
        this.totalPost = totalPost;
    }

    public Integer getTotalFollowers() {
        return totalFollowers;
    }

    public void setTotalFollowers(Integer totalFollowers) {
        this.totalFollowers = totalFollowers;
    }

    public Integer getTotalFollowing() {
        return totalFollowing;
    }

    public void setTotalFollowing(Integer totalFollowing) {
        this.totalFollowing = totalFollowing;
    }
}
