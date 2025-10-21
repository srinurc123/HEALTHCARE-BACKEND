package com.code.healthcare.appointment_system.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.code.healthcare.appointment_system.model.Appointment;
import com.code.healthcare.appointment_system.service.AppointmentService;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService apptService;
    public AppointmentController(AppointmentService apptService) {
        this.apptService = apptService;
    }

    @PostMapping("/book")
    public ResponseEntity<Appointment> book(
        @RequestParam Long patientId,
        @RequestParam Long doctorId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime time) {
        Appointment appt = apptService.bookAppointment(patientId, doctorId, time);
        return ResponseEntity.ok(appt);
    }

    // ADD THIS METHOD - Get appointments by patient ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable Long patientId) {
        List<Appointment> appointments = apptService.getPatientAppointments(patientId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<Appointment>> listByDoctor(
        @PathVariable Long id,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to) {
        return ResponseEntity.ok(apptService.getDoctorAppointments(id, from, to));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Appointment> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(apptService.cancelAppointment(id));
    }

    // ADD THIS METHOD - Complete appointment
    @PutMapping("/{id}/complete")
    public ResponseEntity<Appointment> complete(@PathVariable Long id) {
        return ResponseEntity.ok(apptService.completeAppointment(id));
    }
}
