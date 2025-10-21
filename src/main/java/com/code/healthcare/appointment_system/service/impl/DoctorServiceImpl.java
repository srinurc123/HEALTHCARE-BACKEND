package com.code.healthcare.appointment_system.service.impl;

import org.springframework.stereotype.Service;
import com.code.healthcare.appointment_system.service.DoctorService;
import com.code.healthcare.appointment_system.model.Doctor;
import com.code.healthcare.appointment_system.repository.DoctorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
    @Override
    public List<Doctor> findBySpecialization(String spec) {
        return doctorRepository.findBySpecialization(spec);
    }
}
