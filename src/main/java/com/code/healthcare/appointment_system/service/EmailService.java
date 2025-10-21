package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.model.Appointment;

public interface EmailService {
    void sendAppointmentConfirmation(Appointment appointment);
    void sendAppointmentReminder(Appointment appointment);
    void sendCancellationNotification(Appointment appointment);
}
