package com.example.microserviceProject.repository;

import com.example.microserviceProject.entity.CreatePost;
import com.example.microserviceProject.entity.UserRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Integer>{

    @Query("select a from UserRequest a WHERE a.userIdGetRequest = :userIdGetRequest and a.isRequestSendOrCancel = true")
    List<UserRequest> getdata(@Param("userIdGetRequest") Integer userIdGetRequest);

    boolean existsByUserIdSendRequestAndUserIdGetRequest(Integer userIdSendRequest, Integer userIdGetRequest);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_request ur " +
            "SET is_get_request_accept_or_reject = true, " +
            "is_request_send_or_cancel = false " +
            "WHERE ur.user_id_get_request = :getRequestUserId " +
            "AND ur.user_id_send_request = :sendRequestUserId",
            nativeQuery = true)
    Integer saveAcceptRequest(@Param("sendRequestUserId") Integer sendRequestUserId,
                              @Param("getRequestUserId") Integer getRequestUserId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_request ur " +
            "SET is_get_request_accept_or_reject = false, " +
            "is_request_send_or_cancel = false " +
            "WHERE ur.user_id_get_request = :getRequestUserId " +
            "AND ur.user_id_send_request = :sendRequestUserId",
            nativeQuery = true)
    Integer removeFromFollowing(@Param("sendRequestUserId") Integer sendRequestUserId,
                              @Param("getRequestUserId") Integer getRequestUserId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE profile_details " +
            "SET total_following = total_following - 1 " +
            "WHERE user_id = :sendRequestUserId",
            nativeQuery = true)
    int decreaseFollowing(@Param("sendRequestUserId") Integer sendRequestUserId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE profile_details " +
            "SET total_followers = total_followers - 1 " +
            "WHERE user_id = :getRequestUserId",
            nativeQuery = true)
    int decreaseFollowers(@Param("getRequestUserId") Integer getRequestUserId);
}
