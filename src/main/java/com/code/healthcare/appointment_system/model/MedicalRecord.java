package com.code.healthcare.appointment_system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MedicalRecord extends Auditable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    
    private LocalDate recordDate;
    
    @Column(length = 2000)
    private String diagnosis;
    
    @Column(length = 2000)
    private String symptoms;
    
    @Column(length = 2000)
    private String notes;
    
    private String bloodPressure;
    private String temperature;
    private String weight;
}
