package com.code.healthcare.appointment_system.model;


import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Doctor extends User {
    private String firstName;
    private String lastName;
    private String specialization;
    
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;
}
