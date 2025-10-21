package com.code.healthcare.appointment_system.service.impl;

import org.springframework.stereotype.Service;
import com.code.healthcare.appointment_system.service.PatientService;
import com.code.healthcare.appointment_system.model.Patient;
import com.code.healthcare.appointment_system.repository.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repo;

    @Override
    public List<Patient> getAllPatients() {
        return repo.findAll();
    }
    @Override
    public Patient getPatientById(Long id) {
        return repo.findById(id)
          .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
