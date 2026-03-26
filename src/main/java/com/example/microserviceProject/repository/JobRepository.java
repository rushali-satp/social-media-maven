package com.example.microserviceProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.microserviceProject.entity.Job;

public interface JobRepository extends JpaRepository<Job , Long> {
}
