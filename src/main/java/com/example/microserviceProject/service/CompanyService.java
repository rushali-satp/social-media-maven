package com.example.microserviceProject.service;

import com.example.microserviceProject.entity.Company;

import java.util.List;

public interface CompanyService {

    public List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
}
