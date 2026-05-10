package com.example.microserviceProject.service;

import com.example.microserviceProject.Dto.StatusDTO;
import com.example.microserviceProject.entity.StatusEntity;
import com.example.microserviceProject.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public String addStatus(StatusEntity status) {
        if(status.getStatusText() == null ||
                status.getStatusText().trim().isEmpty()) {

            return "Status cannot be empty";
        }
        status.setCreatedOn(LocalDateTime.now());
        statusRepository.save(status);

        return "Status added successfully";
    }

    @Override
    public List<StatusDTO> getAllStatus() {
        return statusRepository.getAllStatus();
    }
}
