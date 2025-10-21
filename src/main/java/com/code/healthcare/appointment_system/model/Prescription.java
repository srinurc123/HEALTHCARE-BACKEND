package com.code.healthcare.appointment_system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Prescription extends Auditable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    
    private LocalDate prescriptionDate;
    
    @Column(length = 2000)
    private String medications;
    
    @Column(length = 1000)
    private String dosage;
    
    @Column(length = 1000)
    private String instructions;
    
    private Integer durationDays;
}
