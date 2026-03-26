package com.example.microserviceProject.form;

import org.springframework.web.multipart.MultipartFile;

public class BaseForm {

    private String userLoginId;
    private Integer userId;
    private String bioData;
    private MultipartFile image;
    private Integer totalPost;
    private Integer totalFollowers;
    private Integer totalFollowing;
    private Integer sendRequestUserId;
    private Integer getRequestUserId;

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

    public String getBioData() {
        return bioData;
    }

    public void setBioData(String bioData) {
        this.bioData = bioData;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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

    public Integer getSendRequestUserId() {
        return sendRequestUserId;
    }

    public void setSendRequestUserId(Integer sendRequestUserId) {
        this.sendRequestUserId = sendRequestUserId;
    }

    public Integer getGetRequestUserId() {
        return getRequestUserId;
    }

    public void setGetRequestUserId(Integer getRequestUserId) {
        this.getRequestUserId = getRequestUserId;
    }
}
