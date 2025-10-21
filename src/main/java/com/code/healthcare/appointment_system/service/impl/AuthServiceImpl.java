package com.code.healthcare.appointment_system.service.impl;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.code.healthcare.appointment_system.service.AuthService;
import com.code.healthcare.appointment_system.dto.request.*;
import com.code.healthcare.appointment_system.dto.response.JwtResponse;
import com.code.healthcare.appointment_system.security.CustomUserDetails;
import com.code.healthcare.appointment_system.security.JwtTokenProvider;
import com.code.healthcare.appointment_system.model.*;
import com.code.healthcare.appointment_system.repository.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;

    @Override
    public JwtResponse authenticateUser(LoginRequest req) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        String token = tokenProvider.generateToken(auth);
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return new JwtResponse(token, "Bearer",
            userDetails.getId(), userDetails.getUsername(),
            userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Override
    public String registerUser(SignupRequest req) {
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        
        if ("DOCTOR".equalsIgnoreCase(req.getRole())) {
            Doctor doctor = new Doctor();
            doctor.setEmail(req.getEmail());
            doctor.setPassword(encoder.encode(req.getPassword()));
            doctor.setRole("DOCTOR");
            doctor.setFirstName(req.getFirstName());
            doctor.setLastName(req.getLastName());
            doctor.setSpecialization(req.getSpecialization());
            doctorRepo.save(doctor);
        } else {
            Patient patient = new Patient();
            patient.setEmail(req.getEmail());
            patient.setPassword(encoder.encode(req.getPassword()));
            patient.setRole("PATIENT");
            patient.setFirstName(req.getFirstName());
            patient.setLastName(req.getLastName());
            patient.setPhone(req.getPhone());
            patientRepo.save(patient);
        }
        
        return "User registered successfully";
    }
}
