package com.code.healthcare.appointment_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import com.code.healthcare.appointment_system.model.Prescription;
import com.code.healthcare.appointment_system.dto.request.PrescriptionRequest;
import com.code.healthcare.appointment_system.service.PrescriptionService;
import com.code.healthcare.appointment_system.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService service;
    
    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Prescription> create(@RequestBody PrescriptionRequest request) {
        if (request.getDoctorId() == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomUserDetails) {
                request.setDoctorId(((CustomUserDetails) principal).getId());
            }
        }
        return ResponseEntity.ok(service.createPrescription(request));
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Prescription>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getPatientPrescriptions(patientId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPrescriptionById(id));
    }
}
