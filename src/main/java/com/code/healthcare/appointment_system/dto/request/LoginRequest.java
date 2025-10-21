package com.code.healthcare.appointment_system.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter @Setter public class LoginRequest {
    @Email @NotBlank private String email;
    @NotBlank private String password;
}
