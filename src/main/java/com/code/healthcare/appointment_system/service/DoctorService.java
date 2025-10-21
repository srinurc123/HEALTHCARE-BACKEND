package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.model.Doctor;
import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    List<Doctor> findBySpecialization(String spec);
}
