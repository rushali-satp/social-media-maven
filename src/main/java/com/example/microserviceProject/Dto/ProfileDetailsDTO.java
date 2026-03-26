package com.example.microserviceProject.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class ProfileDetailsDTO {

    private String userLoginId;
    private Integer userId;
    private String userOfficialName;
    private String bioDetails;
    private String profileImageName;
    private String profileImagePath;
    private Integer totalPost;
    private Integer totalFollowers;
    private Integer totalFollowing;
    private Boolean isRequestSendOrCancel;
    private Boolean isGetRequestAcceptOrReject;

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

    public String getUserOfficialName() {
        return userOfficialName;
    }

    public void setUserOfficialName(String userOfficialName) {
        this.userOfficialName = userOfficialName;
    }

    public String getBioDetails() {
        return bioDetails;
    }

    public void setBioDetails(String bioDetails) {
        this.bioDetails = bioDetails;
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

    public Boolean getRequestSendOrCancel() {
        return isRequestSendOrCancel;
    }

    public void setRequestSendOrCancel(Boolean requestSendOrCancel) {
        isRequestSendOrCancel = requestSendOrCancel;
    }

    public Boolean getGetRequestAcceptOrReject() {
        return isGetRequestAcceptOrReject;
    }

    public void setGetRequestAcceptOrReject(Boolean getRequestAcceptOrReject) {
        isGetRequestAcceptOrReject = getRequestAcceptOrReject;
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

    public ProfileDetailsDTO(Integer userId, String userLoginId, String userOfficialName,
                             String bioDetails, String profileImageName, String profileImagePath,
                             Integer totalPost, Integer totalFollowers, Integer totalFollowing,
                             Boolean isRequestSendOrCancel, Boolean isGetRequestAcceptOrReject) {

        this.userId = userId;
        this.userLoginId = userLoginId;
        this.userOfficialName = userOfficialName;
        this.bioDetails = bioDetails;
        this.profileImageName = profileImageName;
        this.profileImagePath = profileImagePath;
        this.totalPost = totalPost;
        this.totalFollowers = totalFollowers;
        this.totalFollowing = totalFollowing;
        this.isRequestSendOrCancel = isRequestSendOrCancel;
        this.isGetRequestAcceptOrReject = isGetRequestAcceptOrReject;
    }
}
