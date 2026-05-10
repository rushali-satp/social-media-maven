package com.example.microserviceProject.repository;


import com.example.microserviceProject.Dto.StatusDTO;
import com.example.microserviceProject.entity.StatusEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

    @Transactional
    @Query("SELECT new com.example.microserviceProject.Dto.StatusDTO(" +
            "p.userId, s.statusId, s.statusText, " +
            "p.profileImageName, p.profileImagePath, p.userLoginId) " +
            "FROM StatusEntity s, ProfileDetails p " +
            "WHERE s.userId = p.userId")
    List<StatusDTO> getAllStatus();
}
