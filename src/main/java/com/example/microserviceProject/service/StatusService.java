package com.example.microserviceProject.service;

import com.example.microserviceProject.Dto.StatusDTO;
import com.example.microserviceProject.entity.StatusEntity;

import java.util.List;

public interface StatusService {
    String addStatus(StatusEntity status);

    List<StatusDTO> getAllStatus();
}
