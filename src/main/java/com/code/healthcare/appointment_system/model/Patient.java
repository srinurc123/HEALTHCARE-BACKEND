package com.code.healthcare.appointment_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patients")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Patient extends User {
    private String firstName;
    private String lastName;
    private String phone;
}
