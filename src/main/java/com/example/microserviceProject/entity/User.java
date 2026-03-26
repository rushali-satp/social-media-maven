package com.example.microserviceProject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "um_user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_login_id", nullable = false)
    private String userLoginId;

    @Column(name = "user_official_name", nullable = false)
    private String userOfficialName;

    @Column(name = "is_user", nullable = false, length = 1)
    private String isUser;

    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "mobile_no", nullable = false, unique = true)
    private String mobileNo;

    // 🔹 No-args constructor (MANDATORY for JPA)
    public User() {
    }

    // 🔹 All-args constructor
    public User(Integer userId, String userLoginId, String userOfficialName,
                String isUser, LocalDateTime createdOn, String password) {
        this.userId = userId;
        this.userLoginId = userLoginId;
        this.userOfficialName = userOfficialName;
        this.isUser = isUser;
        this.createdOn = createdOn;
        this.password = password;
    }

    // 🔹 Getters & Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserOfficialName() {
        return userOfficialName;
    }

    public void setUserOfficialName(String userOfficialName) {
        this.userOfficialName = userOfficialName;
    }

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdOn == null) {
            this.createdOn = LocalDateTime.now();
        }
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
