package com.code.healthcare.appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.healthcare.appointment_system.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
