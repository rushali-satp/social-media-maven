package com.example.microserviceProject.repository;

import com.example.microserviceProject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
