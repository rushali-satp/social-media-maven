package com.example.microserviceProject.service;

import com.example.microserviceProject.entity.User;
import com.example.microserviceProject.entity.UserRequest;
import com.example.microserviceProject.form.BaseForm;
import com.example.microserviceProject.repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserRequestServiceImpl implements UserRequestService{

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Override
    public String sendRequest(UserRequest userRequest) {
        Optional<UserRequest> optionalRequest = userRequestRepository
                .findByUserIdSendRequestAndUserIdGetRequest(
                        userRequest.getUserIdSendRequest(),
                        userRequest.getUserIdGetRequest()
                );

        if (optionalRequest.isPresent()) {
            UserRequest existing = optionalRequest.get();
            if (Boolean.TRUE.equals(existing.getRequestSendOrCancel())) {
                return "Request already sent successfully";
            }
            if (Boolean.FALSE.equals(existing.getRequestSendOrCancel())) {
                if (Boolean.FALSE.equals(existing.getGetRequestAcceptOrReject())) {
                    existing.setRequestSendOrCancel(true);
                    existing.setSend_request_created_on(LocalDateTime.now());

                    userRequestRepository.save(existing);
                    return "Request sent successfully";
                } else {
                    return "Request has been accepted";
                }
            }
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

    @Override
    public String removeFollowers(BaseForm userRequest) {
        Integer sendRequestUserId = userRequest.getSendRequestUserId();
        Integer getRequestUserId = userRequest.getGetRequestUserId();
        Integer Count = userRequestRepository.removeFollowers(sendRequestUserId,getRequestUserId);
        if (Count == null || Count == 0) {
            return "There is no such request";
        }
        userRequestRepository.decreaseFollowing(sendRequestUserId);
        userRequestRepository.decreaseFollowers(getRequestUserId);
        return "User Unfollow successfully";
    }
}
