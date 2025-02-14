package com.raju.firstjobapp.review;

import com.raju.firstjobapp.company.CompanyService;
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
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId,
                                                @RequestBody Review review){
         boolean isReviewSaved = reviewService.createReview(companyId,review);
         if (isReviewSaved)
            return new ResponseEntity<>("Add Review Successfully",HttpStatus.CREATED);
         else
             return new ResponseEntity<>("Review Not Saved",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){

        boolean isReviewUpdate = reviewService.updateReview(companyId,reviewId,review);
        if (isReviewUpdate) {
            return new ResponseEntity<>("Review Update Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review Not Updated", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                            @PathVariable Long  reviewId){


        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),HttpStatus.OK);
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long  reviewId){


        boolean isReviewDeleted = reviewService.deleteReview(companyId,reviewId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review Not deleted", HttpStatus.NOT_FOUND);
        }
    }
}
