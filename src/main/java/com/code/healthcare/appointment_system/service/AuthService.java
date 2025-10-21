package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.dto.request.SignupRequest;
import com.code.healthcare.appointment_system.dto.request.LoginRequest;
import com.code.healthcare.appointment_system.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest request);
    String registerUser(SignupRequest request);
}
