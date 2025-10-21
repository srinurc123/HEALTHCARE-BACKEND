package com.code.healthcare.appointment_system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.code.healthcare.appointment_system.service.AppointmentService;
import com.code.healthcare.appointment_system.model.*;
import com.code.healthcare.appointment_system.repository.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepo,
                                  PatientRepository patientRepo,
                                  DoctorRepository doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Appointment bookAppointment(Long patientId, Long doctorId, LocalDateTime time) {
        Patient patient = patientRepo.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepo.findById(doctorId)
            .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Check existing
        List<Appointment> overlapping = appointmentRepo
            .findByDoctorIdAndAppointmentTimeBetween(doctorId, time.minusMinutes(30), time.plusMinutes(30));
        if (!overlapping.isEmpty()) {
            throw new RuntimeException("Slot unavailable");
        }

        Appointment appt = new Appointment();
        appt.setPatient(patient);
        appt.setDoctor(doctor);
        appt.setAppointmentTime(time);
        appt.setStatus(Appointment.Status.PENDING);
        return appointmentRepo.save(appt);
    }

    @Override
    public List<Appointment> getDoctorAppointments(Long doctorId, LocalDateTime from, LocalDateTime to) {
        return appointmentRepo.findByDoctorIdAndAppointmentTimeBetween(doctorId, from, to);
    }

    // ADD THIS METHOD
    @Override
    public List<Appointment> getPatientAppointments(Long patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    @Override
    public Appointment cancelAppointment(Long appointmentId) {
        Appointment appt = appointmentRepo.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appt.setStatus(Appointment.Status.CANCELLED);
        return appointmentRepo.save(appt);
    }

    // ADD THIS METHOD
    @Override
    public Appointment completeAppointment(Long appointmentId) {
        Appointment appt = appointmentRepo.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appt.setStatus(Appointment.Status.COMPLETED);
        return appointmentRepo.save(appt);
    }
}
