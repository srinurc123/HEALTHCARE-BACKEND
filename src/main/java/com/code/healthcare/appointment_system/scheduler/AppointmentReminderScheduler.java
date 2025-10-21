package com.code.healthcare.appointment_system.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.code.healthcare.appointment_system.model.Appointment;
import com.code.healthcare.appointment_system.repository.AppointmentRepository;
import com.code.healthcare.appointment_system.service.EmailService;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentReminderScheduler {
    private final AppointmentRepository appointmentRepo;
    private final EmailService emailService;

    @Scheduled(cron = "0 0 9 * * ?")  // Daily at 9 AM
    public void sendDailyReminders() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        LocalDateTime startOfDay = tomorrow.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = tomorrow.toLocalDate().atTime(23, 59);

        List<Appointment> appointments = appointmentRepo
            .findByDoctorIdAndAppointmentTimeBetween(null, startOfDay, endOfDay);

        appointments.forEach(appt -> {
            if (appt.getStatus() == Appointment.Status.CONFIRMED) {
                emailService.sendAppointmentReminder(appt);
            }
        });
    }
}
