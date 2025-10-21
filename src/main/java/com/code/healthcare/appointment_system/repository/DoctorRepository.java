package com.code.healthcare.appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.healthcare.appointment_system.model.Doctor;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialization(String specialization);
}
