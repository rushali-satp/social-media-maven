package com.example.microserviceProject.service;

import java.util.List;
import com.example.microserviceProject.entity.UserRequest;
import com.example.microserviceProject.form.BaseForm;

public interface UserRequestService {
    String sendRequest(UserRequest userRequest);

    List<UserRequest> getUser(UserRequest userRequest);

    String removeFromFollowing(BaseForm userRequest);

    String removeFollowers(BaseForm userRequest);
}
