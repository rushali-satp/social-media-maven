package com.example.microserviceProject.review;

import com.example.microserviceProject.entity.Review;
import com.example.microserviceProject.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review ){

        boolean issavedReview = reviewService.addreview(companyId,review);
        if(issavedReview) {
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not added Successfully", HttpStatus.OK);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getreview (@PathVariable Long companyId, @PathVariable Long reviewId){

        return new ResponseEntity<>(reviewService.getreview(companyId, reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,@RequestBody Review review){
        boolean isReviewUpsated = reviewService.updatereview(companyId, reviewId, review);
        if(isReviewUpsated){
            return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Not Updated Successfully",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){
        boolean isReviewdeleted = reviewService.deleteReview(companyId, reviewId);
        if(isReviewdeleted){
            return new ResponseEntity<>("Review deleted Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Not deleted Successfully",HttpStatus.NOT_FOUND);
        }
    }
}
