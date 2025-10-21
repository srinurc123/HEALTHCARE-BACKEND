package com.code.healthcare.appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.healthcare.appointment_system.model.MedicalRecord;
import java.util.List;
import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientId(Long patientId);
    List<MedicalRecord> findByDoctorId(Long doctorId);
    Optional<MedicalRecord> findByAppointmentId(Long appointmentId);
}
