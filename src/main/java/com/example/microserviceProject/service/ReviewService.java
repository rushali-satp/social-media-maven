package com.example.microserviceProject.service;

import com.example.microserviceProject.entity.Company;
import com.example.microserviceProject.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean addreview(Long companyId,Review review);
    Review getreview(Long companyId,Long reviewId);
    boolean updatereview(Long companyId,Long reviewId, Review review);
    boolean deleteReview(Long companyId,Long reviewId);

}
