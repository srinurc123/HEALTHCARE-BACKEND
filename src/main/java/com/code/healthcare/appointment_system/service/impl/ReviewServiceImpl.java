package com.code.healthcare.appointment_system.service.impl;

import org.springframework.stereotype.Service;
import com.code.healthcare.appointment_system.service.ReviewService;
import com.code.healthcare.appointment_system.model.Review;
import com.code.healthcare.appointment_system.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repo;
    
    @Override
    public Review addReview(Review review) {
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }
        return repo.save(review);
    }
    
    @Override
    public List<Review> getDoctorReviews(Long doctorId) {
        return repo.findByDoctorId(doctorId);
    }
    
    @Override
    public Double getDoctorAverageRating(Long doctorId) {
        Double avg = repo.getAverageRatingByDoctorId(doctorId);
        return avg != null ? avg : 0.0;
    }
}
