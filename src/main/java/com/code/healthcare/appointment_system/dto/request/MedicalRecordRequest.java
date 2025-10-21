package com.code.healthcare.appointment_system.dto.request;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordRequest {
    private Long patientId;
    private Long doctorId;
    private Long appointmentId;
    private LocalDate recordDate;
    private String diagnosis;
    private String symptoms;
    private String notes;
    private String bloodPressure;
    private String temperature;
    private String weight;
}
