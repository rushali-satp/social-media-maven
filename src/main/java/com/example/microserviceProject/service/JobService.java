package com.example.microserviceProject.service;

import com.example.microserviceProject.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);
    boolean deleteJobById(Long id);

    boolean updateJob(long id, Job updateedJob);
}
