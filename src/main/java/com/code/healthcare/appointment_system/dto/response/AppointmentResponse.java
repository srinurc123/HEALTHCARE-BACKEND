package com.code.healthcare.appointment_system.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentTime;
    private String status;
    private String patientName;
    private String doctorName;
    private String specialization;
}
