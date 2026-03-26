package com.example.microserviceProject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "create_post", schema = "public")
public class CreatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "user_login_id", nullable = false)
    private String userLoginId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "post_text", nullable = false, length = 500)
    private String postText;

    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @Column(name = "post_image_name", nullable = false)
    private String postImageName;

    @Column(name = "post_image_path", nullable = false)
    private String postImagePath;

    @Column(name = "post_image_size", nullable = false)
    private String postImageSize;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostComments> comments;
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

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getPostImageName() {
        return postImageName;
    }

    public void setPostImageName(String postImageName) {
        this.postImageName = postImageName;
    }

    public String getPostImagePath() {
        return postImagePath;
    }

    public void setPostImagePath(String postImagePath) {
        this.postImagePath = postImagePath;
    }

    public String getPostImageSize() {
        return postImageSize;
    }

    public void setPostImageSize(String postImageSize) {
        this.postImageSize = postImageSize;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdOn == null) {
            this.createdOn = LocalDateTime.now();
        }
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<PostComments> getComments() {
        return comments;
    }

    public void setComments(List<PostComments> comments) {
        this.comments = comments;
    }
}
