package com.code.healthcare.appointment_system.dto.response;

import lombok.*;

@Getter @Setter @AllArgsConstructor public class JwtResponse {
    private String token;
    private String tokenType = "Bearer";
    private Long userId;
    private String email;
    private String role;
}
