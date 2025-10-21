package com.code.healthcare.appointment_system.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter @Setter public class SignupRequest {
    @Email @NotBlank private String email;
    @NotBlank private String password;
    @NotBlank private String role;    // PATIENT, DOCTOR
    private String firstName;
    private String lastName;
    private String specialization;     // for doctor only
    private String phone;              // for patient only
}
