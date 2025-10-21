package com.code.healthcare.appointment_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.code.healthcare.appointment_system.model.Review;
import com.code.healthcare.appointment_system.service.ReviewService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;
    
    @PostMapping
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.ok(service.addReview(review));
    }
    
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Review>> getDoctorReviews(@PathVariable Long doctorId) {
        return ResponseEntity.ok(service.getDoctorReviews(doctorId));
    }
    
    @GetMapping("/doctor/{doctorId}/rating")
    public ResponseEntity<Double> getDoctorRating(@PathVariable Long doctorId) {
        return ResponseEntity.ok(service.getDoctorAverageRating(doctorId));
    }
}
