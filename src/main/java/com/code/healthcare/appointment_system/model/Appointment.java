package com.code.healthcare.appointment_system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Appointment extends Auditable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private Status status;    // PENDING, CONFIRMED, CANCELLED, COMPLETED

    @ManyToOne @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public enum Status {
        PENDING, CONFIRMED, CANCELLED, COMPLETED
    }
}
