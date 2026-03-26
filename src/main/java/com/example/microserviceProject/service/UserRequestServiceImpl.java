package com.example.microserviceProject.service;

import com.example.microserviceProject.entity.User;
import com.example.microserviceProject.entity.UserRequest;
import com.example.microserviceProject.form.BaseForm;
import com.example.microserviceProject.repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserRequestServiceImpl implements UserRequestService{

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Override
    public String sendRequest(UserRequest userRequest) {
        boolean exists = userRequestRepository
                .existsByUserIdSendRequestAndUserIdGetRequest(
                        userRequest.getUserIdSendRequest(),
                        userRequest.getUserIdGetRequest()
                );

        if (exists) {
            return "Request already sent successfully";
        }
        UserRequest getdata = new UserRequest();
        getdata.setUserIdSendRequest(userRequest.getUserIdSendRequest());
        getdata.setUserIdGetRequest(userRequest.getUserIdGetRequest());
        getdata.setRequestSendOrCancel(true);
        getdata.setGetRequestAcceptOrReject(false);
        getdata.setSend_request_created_on(LocalDateTime.now());
        userRequestRepository.save(getdata);
        return "Request send successfully";
    }

    @Override
    public List<UserRequest> getUser(UserRequest userRequest) {
        return userRequestRepository.getdata(userRequest.getUserIdGetRequest());
    }

    @Override
    public String removeFromFollowing(BaseForm userRequest) {
        Integer sendRequestUserId = userRequest.getSendRequestUserId();
        Integer getRequestUserId = userRequest.getGetRequestUserId();
        Integer Count = userRequestRepository.removeFromFollowing(sendRequestUserId,getRequestUserId);
        if (Count == null || Count == 0) {
            return "There is no such request";
        }
        userRequestRepository.decreaseFollowing(sendRequestUserId);
        userRequestRepository.decreaseFollowers(getRequestUserId);
        return "User Unfollow successfully";
    }
}
