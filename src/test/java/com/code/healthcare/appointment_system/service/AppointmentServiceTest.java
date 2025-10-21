package com.code.healthcare.appointment_system.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.code.healthcare.appointment_system.model.*;
import com.code.healthcare.appointment_system.repository.*;
import com.code.healthcare.appointment_system.service.impl.AppointmentServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class AppointmentServiceTest {
    
    @Mock
    private AppointmentRepository appointmentRepo;
    @Mock
    private PatientRepository patientRepo;
    @Mock
    private DoctorRepository doctorRepo;
    
    @InjectMocks
    private AppointmentServiceImpl appointmentService;
    
    private Patient patient;
    private Doctor doctor;
    
    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("John");
        
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFirstName("Smith");
    }
    
    @Test
    void testBookAppointment_Success() {
        LocalDateTime time = LocalDateTime.now().plusDays(1);
        
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient));
        when(doctorRepo.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepo.findByDoctorIdAndAppointmentTimeBetween(
            anyLong(), any(), any())).thenReturn(List.of());
        when(appointmentRepo.save(any())).thenAnswer(i -> i.getArgument(0));
        
        Appointment result = appointmentService.bookAppointment(1L, 1L, time);
        
        assertNotNull(result);
        assertEquals(Appointment.Status.PENDING, result.getStatus());
        verify(appointmentRepo, times(1)).save(any());
    }
    
    @Test
    void testBookAppointment_SlotUnavailable() {
        LocalDateTime time = LocalDateTime.now().plusDays(1);
        Appointment existing = new Appointment();
        
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient));
        when(doctorRepo.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepo.findByDoctorIdAndAppointmentTimeBetween(
            anyLong(), any(), any())).thenReturn(List.of(existing));
        
        assertThrows(RuntimeException.class, 
            () -> appointmentService.bookAppointment(1L, 1L, time));
    }
    
    @Test
    void testCancelAppointment() {
        Appointment appt = new Appointment();
        appt.setId(1L);
        appt.setStatus(Appointment.Status.PENDING);
        
        when(appointmentRepo.findById(1L)).thenReturn(Optional.of(appt));
        when(appointmentRepo.save(any())).thenAnswer(i -> i.getArgument(0));
        
        Appointment result = appointmentService.cancelAppointment(1L);
        
        assertEquals(Appointment.Status.CANCELLED, result.getStatus());
    }
}
