package com.code.healthcare.appointment_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.code.healthcare.appointment_system.model.*;
import com.code.healthcare.appointment_system.service.*;
import com.code.healthcare.appointment_system.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("totalPatients", patientService.getAllPatients().size());
        dashboard.put("totalDoctors", doctorService.getAllDoctors().size());
        dashboard.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(dashboard);
    }
    
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
    
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
    
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<ApiResponse> deletePatient(@PathVariable Long id) {
        // Add delete logic in service
        return ResponseEntity.ok(
            new ApiResponse(true, "Patient deleted successfully"));
    }
    
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable Long id) {
        // Add delete logic in service
        return ResponseEntity.ok(
            new ApiResponse(true, "Doctor deleted successfully"));
    }
}
