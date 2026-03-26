package com.example.microserviceProject.service;

import com.example.microserviceProject.entity.Job;
import com.example.microserviceProject.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    private long nextId = 1L;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
        jobRepository.save(job);

    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id){
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(long id, Job updateedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setDescription(updateedJob.getDescription());
                job.setTitle(updateedJob.getTitle());
                job.setMinSalary(updateedJob.getMinSalary());
                job.setMaxSalary(updateedJob.getMaxSalary());
                job.setLocation(updateedJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}
