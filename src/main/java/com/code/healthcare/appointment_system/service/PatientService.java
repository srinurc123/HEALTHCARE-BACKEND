package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.model.Patient;
import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
}
