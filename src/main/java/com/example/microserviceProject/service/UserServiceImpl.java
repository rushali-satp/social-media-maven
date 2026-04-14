package com.example.microserviceProject.service;

import com.example.microserviceProject.Dto.ProfileDetailsDTO;
import com.example.microserviceProject.entity.ProfileDetails;
import com.example.microserviceProject.entity.User;
import com.example.microserviceProject.form.BaseForm;
import com.example.microserviceProject.repository.ProfileDetailsRepository;
import com.example.microserviceProject.repository.UserCreationRepository;
import com.example.microserviceProject.repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserCreationRepository userCreationRepository;

    @Autowired
    private ProfileDetailsRepository profileDetailsRepository;

    @Autowired
    private UserRequestRepository userRequestRepository;


    private final String UPLOAD_DIR = "D:/uploadsPostImage/";

    public String createUser(User user) {
        String mobileNo = user.getMobileNo();
        if (mobileNo == null || !mobileNo.matches("^[6-9]\\d{9}$")) {
            throw new IllegalArgumentException(
                    "Invalid mobile number. It must be 10 digits and start with 6, 7, 8, or 9"
            );
        }
        String email = user.getEmailId();
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        LocalDateTime createdOn =
                user.getCreatedOn() != null ? user.getCreatedOn() : LocalDateTime.now();

        User savedUser = userCreationRepository.save(user);

        if (savedUser.getUserId() != null) {
            return "User data saved successfully";
        } else {
            throw new RuntimeException("User insert failed");
        }
    }

    @Override
    public User getUser(Integer id) {
        return userCreationRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<User> getUserLoginDetails(String username, String password){
        return userCreationRepository.getUserLoginDetails(username, password);
    }

    @Override
    public String createProfileDetails(BaseForm baseForm) {
        try {
            ProfileDetails profileDetails = profileDetailsRepository.findByUserId(baseForm.getUserId());

            // ✅ If profile already exists → update
            if (profileDetails != null) {
                profileDetails.setBioDetails(baseForm.getBioData());

                if (baseForm.getImage() != null && !baseForm.getImage().isEmpty()) {

                    String fileName = System.currentTimeMillis() + "_" + baseForm.getImage().getOriginalFilename();
                    Path filePath = Paths.get(UPLOAD_DIR + fileName);

                    Files.createDirectories(filePath.getParent());
                    Files.write(filePath, baseForm.getImage().getBytes());

                    profileDetails.setProfileImageName(fileName);
                    profileDetails.setProfileImagePath(filePath.toString());
                    profileDetails.setProfileImageSize(String.valueOf(baseForm.getImage().getSize()));
                }

                profileDetailsRepository.save(profileDetails);

                return "Profile updated successfully";

            }
            profileDetails = new ProfileDetails();
            profileDetails.setBioDetails(baseForm.getBioData());
            profileDetails.setUserId(baseForm.getUserId());
            profileDetails.setUserLoginId(baseForm.getUserLoginId());
            profileDetails.setTotalFollowers(baseForm.getTotalFollowers());
            profileDetails.setTotalFollowing(baseForm.getTotalFollowing());
            profileDetails.setTotalPost(baseForm.getTotalPost());
            profileDetails.setCreatedOn(LocalDateTime.now());
            if (baseForm.getImage() != null && !baseForm.getImage().isEmpty()) {

                String fileName = System.currentTimeMillis() + "_" + baseForm.getImage().getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);

                Files.createDirectories(filePath.getParent());
                Files.write(filePath, baseForm.getImage().getBytes());

                profileDetails.setProfileImageName(fileName);
                profileDetails.setProfileImagePath(filePath.toString());
                profileDetails.setProfileImageSize(String.valueOf(baseForm.getImage().getSize())); // bytes
            }
            profileDetailsRepository.save(profileDetails);
            return "User Profile Details created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while creating profile details";
        }
    }

    @Override
    public List<ProfileDetails> getPostDetails(Integer userId) {
        return profileDetailsRepository.getProfileDetails(userId);
    }

    @Override
    public List<ProfileDetailsDTO> getALLProfileDetails(Integer userId) {
        return userCreationRepository.getALLProfileDetails(userId);
    }

    @Override
    public List<ProfileDetailsDTO> getRequestProfileDetails(Integer userId) {
        return userCreationRepository.getRequestProfileDetails(userId);
    }

    @Override
    public String acceptRequestProfile(BaseForm baseForm) {
        Integer sendRequestUserId = baseForm.getSendRequestUserId();
        Integer getRequestUserId = baseForm.getGetRequestUserId();

        Integer requestCount = userRequestRepository.saveAcceptRequest(sendRequestUserId,getRequestUserId);
        if (requestCount == null || requestCount == 0) {
            return "There is no such request";
        }

        Integer profileCount = profileDetailsRepository
                .updateProfileFollowingFollowers(sendRequestUserId, getRequestUserId);
        if (profileCount == null || profileCount == 0) {
            // Optional: rollback scenario (transaction will handle automatically)
            return "Request accepted but profile not updated";
        }

        return "Request accepted successfully";
    }

    @Override
    public List<User> getUserSearchdtls(String userLoginId, String userName) {
        return userCreationRepository.searchUsers(userLoginId, userName);
    }

    @Override
    public List<ProfileDetailsDTO> getSearchProfileDetails(Integer userId, Integer searchUserId) {
        return userCreationRepository.getSearchProfileDetails(userId , searchUserId);

    }

}
