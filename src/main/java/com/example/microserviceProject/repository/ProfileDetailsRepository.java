package com.example.microserviceProject.repository;

import com.example.microserviceProject.Dto.ProfileDetailsDTO;
import com.example.microserviceProject.entity.ProfileDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Repository
public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails, Integer> {

    @Transactional
    @Query("select a from ProfileDetails a WHERE a.userId = :userId")
    List<ProfileDetails> getProfileDetails(@Param("userId") Integer userId);

    ProfileDetails findByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE profile_details pd " +
            "SET " +
            "total_followers = CASE " +
            "    WHEN pd.user_id = :getRequestUserId THEN COALESCE(pd.total_followers, 0) + 1 " +
            "    ELSE pd.total_followers END, " +
            "total_following = CASE " +
            "    WHEN pd.user_id = :sendRequestUserId THEN COALESCE(pd.total_following, 0) + 1 " +
            "    ELSE pd.total_following END " +
            "WHERE pd.user_id IN (:getRequestUserId, :sendRequestUserId)",
            nativeQuery = true)
    Integer updateProfileFollowingFollowers(
            @Param("sendRequestUserId") Integer sendRequestUserId,
            @Param("getRequestUserId") Integer getRequestUserId);
}
