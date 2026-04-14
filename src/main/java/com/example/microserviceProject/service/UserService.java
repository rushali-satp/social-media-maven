package com.example.microserviceProject.service;

import com.example.microserviceProject.Dto.ProfileDetailsDTO;
import com.example.microserviceProject.entity.ProfileDetails;
import com.example.microserviceProject.entity.User;
import com.example.microserviceProject.form.BaseForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public String createUser(User user);

    public User getUser(Integer id);

    public List<User> getUserLoginDetails(String username, String password);

    String createProfileDetails(BaseForm baseForm);

    List<ProfileDetails> getPostDetails(Integer userId);

    List<ProfileDetailsDTO> getALLProfileDetails(Integer userId);

    public List<ProfileDetailsDTO> getRequestProfileDetails(Integer userId);

    String acceptRequestProfile(BaseForm baseForm);

    List<User> getUserSearchdtls(String userLoginId, String userName);

    public List<ProfileDetailsDTO> getSearchProfileDetails(Integer userId, Integer searchUserId);
}
