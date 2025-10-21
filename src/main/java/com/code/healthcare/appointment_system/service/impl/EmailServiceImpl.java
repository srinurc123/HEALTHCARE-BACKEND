package com.code.healthcare.appointment_system.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.code.healthcare.appointment_system.service.EmailService;
import com.code.healthcare.appointment_system.model.Appointment;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendAppointmentConfirmation(Appointment appt) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(appt.getPatient().getEmail());
        msg.setSubject("Appointment Confirmation");
        msg.setText("Dear " + appt.getPatient().getFirstName() + 
            ",\n\nYour appointment with Dr. " + appt.getDoctor().getFirstName() +
            " " + appt.getDoctor().getLastName() + " is confirmed for " +
            appt.getAppointmentTime() + ".\n\nThank you!");
        mailSender.send(msg);
    }

    @Override
    public void sendAppointmentReminder(Appointment appt) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(appt.getPatient().getEmail());
        msg.setSubject("Appointment Reminder");
        msg.setText("Dear " + appt.getPatient().getFirstName() + 
            ",\n\nReminder: Your appointment with Dr. " + appt.getDoctor().getFirstName() +
            " " + appt.getDoctor().getLastName() + " is scheduled for " +
            appt.getAppointmentTime() + ".\n\nSee you soon!");
        mailSender.send(msg);
    }

    @Override
    public void sendCancellationNotification(Appointment appt) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(appt.getPatient().getEmail());
        msg.setSubject("Appointment Cancelled");
        msg.setText("Dear " + appt.getPatient().getFirstName() + 
            ",\n\nYour appointment with Dr. " + appt.getDoctor().getFirstName() +
            " " + appt.getDoctor().getLastName() + " scheduled for " +
            appt.getAppointmentTime() + " has been cancelled.\n\nThank you!");
        mailSender.send(msg);
    }
}
