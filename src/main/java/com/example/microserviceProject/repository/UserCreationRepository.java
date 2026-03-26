package com.example.microserviceProject.repository;

import com.example.microserviceProject.Dto.ProfileDetailsDTO;
import com.example.microserviceProject.entity.CreatePost;
import com.example.microserviceProject.entity.ProfileDetails;
import com.example.microserviceProject.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserCreationRepository extends JpaRepository<User, Long> {
    @Query(
            value = "SELECT * FROM um_user WHERE user_login_id = :username AND password = :password",
            nativeQuery = true
    )
    List<User> getUserLoginDetails(@Param("username") String username,
                         @Param("password") String password);

    @Query("SELECT new com.example.microserviceProject.Dto.ProfileDetailsDTO(" +
            "u.userId, u.userLoginId, u.userOfficialName, p.bioDetails, p.profileImageName, " +
            "p.profileImagePath, p.totalPost, p.totalFollowers, p.totalFollowing , ur.isRequestSendOrCancel " +
            ", ur.isGetRequestAcceptOrReject ) " +
            "FROM User u " +
            "LEFT JOIN ProfileDetails p ON p.userId = u.userId " +
            "left join UserRequest ur ON ur.userIdSendRequest = :userId AND ur.userIdGetRequest = u.userId " +
            "WHERE u.isUser = 'Y' AND u.userId != :userId " +
            "ORDER BY u.userId")
    List<ProfileDetailsDTO> getALLProfileDetails(@Param("userId") Integer userId);

    @Query("SELECT new com.example.microserviceProject.Dto.ProfileDetailsDTO(" +
            "uu.userId, uu.userLoginId, uu.userOfficialName, pd.bioDetails, pd.profileImageName, " +
            "pd.profileImagePath, pd.totalPost, pd.totalFollowers, pd.totalFollowing , ur.isRequestSendOrCancel " +
            ", ur.isGetRequestAcceptOrReject ) " +
            "FROM UserRequest ur " +
            "JOIN User uu  ON uu.userId = ur.userIdSendRequest " +
            "LEFT JOIN ProfileDetails pd   ON pd.userId = uu.userId " +
            "WHERE uu.isUser = 'Y' AND ur.userIdGetRequest = :userId " +
            "ORDER BY uu.userId")
    List<ProfileDetailsDTO> getRequestProfileDetails(@Param("userId") Integer userId);
}
