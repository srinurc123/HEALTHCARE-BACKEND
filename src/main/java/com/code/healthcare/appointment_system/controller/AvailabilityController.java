package com.code.healthcare.appointment_system.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.code.healthcare.appointment_system.model.AvailabilitySlot;
import com.code.healthcare.appointment_system.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/availability")
@RequiredArgsConstructor
public class AvailabilityController {
    private final AvailabilityService service;

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AvailabilitySlot>> getSlots(
        @PathVariable Long doctorId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
            LocalDateTime date) {
        return ResponseEntity.ok(service.getDoctorAvailableSlots(doctorId, date));
    }

    @PutMapping("/block/{slotId}")
    public ResponseEntity<AvailabilitySlot> blockSlot(@PathVariable Long slotId) {
        return ResponseEntity.ok(service.blockSlot(slotId));
    }
}
