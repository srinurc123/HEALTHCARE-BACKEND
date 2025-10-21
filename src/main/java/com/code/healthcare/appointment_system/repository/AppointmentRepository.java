package com.code.healthcare.appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.healthcare.appointment_system.model.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentTimeBetween(
        Long doctorId, LocalDateTime start, LocalDateTime end);
    
    // ADD THIS METHOD
    List<Appointment> findByPatientId(Long patientId);
}
