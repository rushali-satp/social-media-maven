package com.example.microserviceProject.Dto;

import java.time.LocalDateTime;

public class StatusDTO {

    private Integer userId;
    private Long statusId;
    private String statusText;
    private String profileImageName;
    private String profileImagePath;
    private String userLoginId;
    private LocalDateTime createdOn;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
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

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public StatusDTO(
            Integer userId,
            Long statusId,
            String statusText,
            LocalDateTime createdOn,
            String profileImageName,
            String profileImagePath,
            String userLoginId
    ) {
        this.userId = userId;
        this.statusId = statusId;
        this.statusText = statusText;
        this.createdOn = createdOn ;
        this.profileImageName = profileImageName;
        this.profileImagePath = profileImagePath;
        this.userLoginId = userLoginId;
    }
}
