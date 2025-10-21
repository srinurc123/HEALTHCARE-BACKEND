package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.model.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(Long patientId, Long doctorId, LocalDateTime time);
    List<Appointment> getDoctorAppointments(Long doctorId, LocalDateTime from, LocalDateTime to);
    List<Appointment> getPatientAppointments(Long patientId); // ADD THIS
    Appointment cancelAppointment(Long appointmentId);
    Appointment completeAppointment(Long appointmentId); // ADD THIS
}
