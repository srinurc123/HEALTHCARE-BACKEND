package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.model.Review;
import java.util.List;

public interface ReviewService {
    Review addReview(Review review);
    List<Review> getDoctorReviews(Long doctorId);
    Double getDoctorAverageRating(Long doctorId);
}
