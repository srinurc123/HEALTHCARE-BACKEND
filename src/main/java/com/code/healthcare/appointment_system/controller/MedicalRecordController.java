package com.code.healthcare.appointment_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import com.code.healthcare.appointment_system.model.MedicalRecord;
import com.code.healthcare.appointment_system.dto.request.MedicalRecordRequest;
import com.code.healthcare.appointment_system.service.MedicalRecordService;
import com.code.healthcare.appointment_system.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService service;
    
    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<MedicalRecord> create(@RequestBody MedicalRecordRequest request) {
        // If the frontend didn't include doctorId, derive it from the authenticated user
        if (request.getDoctorId() == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomUserDetails) {
                request.setDoctorId(((CustomUserDetails) principal).getId());
            }
        }

        return ResponseEntity.ok(service.createRecord(request));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<MedicalRecord> update(@PathVariable Long id, 
                                                @RequestBody MedicalRecordRequest request) {
        return ResponseEntity.ok(service.updateRecord(id, request));
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getPatientRecords(patientId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecordById(id));
    }
}
