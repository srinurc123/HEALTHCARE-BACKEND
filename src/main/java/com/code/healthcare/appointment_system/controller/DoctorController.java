package com.code.healthcare.appointment_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.code.healthcare.appointment_system.dto.response.DoctorResponse;
import com.code.healthcare.appointment_system.model.Doctor;
import com.code.healthcare.appointment_system.service.DoctorService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService service;

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> list() {
        List<Doctor> doctors = service.getAllDoctors();
        List<DoctorResponse> responses = doctors.stream()
            .map(doc -> new DoctorResponse(
                doc.getId(),
                doc.getFirstName(),
                doc.getLastName(),
                doc.getSpecialization(),
                doc.getEmail()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Doctor> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDoctorById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> search(@RequestParam(required = false) String specialization) {
        if (specialization == null || specialization.isEmpty()) {
            return ResponseEntity.ok(service.getAllDoctors());
        }
        return ResponseEntity.ok(service.findBySpecialization(specialization));
    }
}
