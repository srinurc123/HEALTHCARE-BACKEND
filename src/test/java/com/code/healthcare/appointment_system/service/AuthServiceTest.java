package com.code.healthcare.appointment_system.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.code.healthcare.appointment_system.dto.request.SignupRequest;
import com.code.healthcare.appointment_system.repository.*;
import com.code.healthcare.appointment_system.security.JwtTokenProvider;
import com.code.healthcare.appointment_system.service.impl.AuthServiceImpl;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
  class AuthServiceTest {
    
    @Mock
    private AuthenticationManager authManager;
    @Mock
    private UserRepository userRepo;
    @Mock
    private PatientRepository patientRepo;
    @Mock
    private DoctorRepository doctorRepo;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private JwtTokenProvider tokenProvider;
    
    @InjectMocks
    private AuthServiceImpl authService;
    
    @Test
    void testRegisterPatient() {
        SignupRequest req = new SignupRequest();
        req.setEmail("test@test.com");
        req.setPassword("password");
        req.setRole("PATIENT");
        req.setFirstName("John");
        
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.empty());
        when(encoder.encode(anyString())).thenReturn("encoded");
        when(patientRepo.save(any())).thenAnswer(i -> i.getArgument(0));
        
        String result = authService.registerUser(req);
        
        assertEquals("User registered successfully", result);
        verify(patientRepo, times(1)).save(any());
    }
    
    @Test
    void testRegisterDoctor() {
        SignupRequest req = new SignupRequest();
        req.setEmail("doc@test.com");
        req.setPassword("password");
        req.setRole("DOCTOR");
        req.setFirstName("Smith");
        req.setSpecialization("Cardiology");
        
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.empty());
        when(encoder.encode(anyString())).thenReturn("encoded");
        when(doctorRepo.save(any())).thenAnswer(i -> i.getArgument(0));
        
        String result = authService.registerUser(req);
        
        assertEquals("User registered successfully", result);
        verify(doctorRepo, times(1)).save(any());
    }
}
