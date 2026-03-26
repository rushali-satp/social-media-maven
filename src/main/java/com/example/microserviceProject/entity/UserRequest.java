package com.example.microserviceProject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_request", schema = "public")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;

    @Column(name = "user_id_send_request", nullable = false)
    private Integer userIdSendRequest;

    @Column(name = "user_id_get_request", nullable = false)
    private Integer userIdGetRequest;

    @Column(name = "is_get_request_accept_or_reject", nullable = false)
    private Boolean isGetRequestAcceptOrReject;

    @Column(name = "is_request_send_or_cancel", nullable = false)
    private Boolean isRequestSendOrCancel;

    @Column(name = "send_request_created_on", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime send_request_created_on;

    @Column(name = "get_request_accept_on" )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime get_request_accept_on;

    @PrePersist
    protected void onCreate() {
        if (this.send_request_created_on == null) {
            this.send_request_created_on = LocalDateTime.now();
        }
    }




    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getUserIdSendRequest() {
        return userIdSendRequest;
    }

    public void setUserIdSendRequest(Integer userIdSendRequest) {
        this.userIdSendRequest = userIdSendRequest;
    }

    public Integer getUserIdGetRequest() {
        return userIdGetRequest;
    }

    public void setUserIdGetRequest(Integer userIdGetRequest) {
        this.userIdGetRequest = userIdGetRequest;
    }

    public Boolean getGetRequestAcceptOrReject() {
        return isGetRequestAcceptOrReject;
    }

    public void setGetRequestAcceptOrReject(Boolean getRequestAcceptOrReject) {
        isGetRequestAcceptOrReject = getRequestAcceptOrReject;
    }

    public Boolean getRequestSendOrCancel() {
        return isRequestSendOrCancel;
    }

    public void setRequestSendOrCancel(Boolean requestSendOrCancel) {
        isRequestSendOrCancel = requestSendOrCancel;
    }

    public LocalDateTime getSend_request_created_on() {
        return send_request_created_on;
    }

    public void setSend_request_created_on(LocalDateTime send_request_created_on) {
        this.send_request_created_on = send_request_created_on;
    }

    public LocalDateTime getGet_request_accept_on() {
        return get_request_accept_on;
    }

    public void setGet_request_accept_on(LocalDateTime get_request_accept_on) {
        this.get_request_accept_on = get_request_accept_on;
    }
}
