package com.code.healthcare.appointment_system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import com.code.healthcare.appointment_system.service.MedicalRecordService;
import com.code.healthcare.appointment_system.model.*;
import com.code.healthcare.appointment_system.repository.*;
import com.code.healthcare.appointment_system.dto.request.MedicalRecordRequest;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository repo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final AppointmentRepository appointmentRepo;
    
    // Import Optional for the repository check
    
    @Override
    public MedicalRecord createRecord(MedicalRecordRequest request) {
        // Allow deriving missing patient/doctor ids from the appointment when possible
        Patient patient = null;
        Doctor doctor = null;

        if (request.getPatientId() != null) {
            patient = patientRepo.findById(request.getPatientId()).orElse(null);
        }

        if (request.getDoctorId() != null) {
            doctor = doctorRepo.findById(request.getDoctorId()).orElse(null);
        }

        if ((patient == null || doctor == null) && request.getAppointmentId() != null) {
            // try to derive from appointment
            Appointment appt = appointmentRepo.findById(request.getAppointmentId()).orElse(null);
            if (appt != null) {
                if (patient == null) patient = appt.getPatient();
                if (doctor == null) doctor = appt.getDoctor();
            }
        }

        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found");
        }
        
        MedicalRecord record = new MedicalRecord();
        record.setPatient(patient);
        record.setDoctor(doctor);
        
        if (request.getAppointmentId() != null) {
            // Prevent creating duplicate medical record for the same appointment
            if (repo.findByAppointmentId(request.getAppointmentId()).isPresent()) {
                throw new IllegalArgumentException("Medical record already exists for this appointment");
            }

            appointmentRepo.findById(request.getAppointmentId()).ifPresentOrElse(
                (appointment) -> record.setAppointment(appointment),
                () -> log.warn("Requested appointment id {} not found - creating record without linking appointment", request.getAppointmentId())
            );
        }
        
        record.setRecordDate(request.getRecordDate());
        record.setDiagnosis(request.getDiagnosis());
        record.setSymptoms(request.getSymptoms());
        record.setNotes(request.getNotes());
        record.setBloodPressure(request.getBloodPressure());
        record.setTemperature(request.getTemperature());
        record.setWeight(request.getWeight());
        
        return repo.save(record);
    }
    
    @Override
    public MedicalRecord updateRecord(Long id, MedicalRecordRequest request) {
        MedicalRecord existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Record not found"));
        
        existing.setDiagnosis(request.getDiagnosis());
        existing.setSymptoms(request.getSymptoms());
        existing.setNotes(request.getNotes());
        existing.setBloodPressure(request.getBloodPressure());
        existing.setTemperature(request.getTemperature());
        existing.setWeight(request.getWeight());
        
        return repo.save(existing);
    }
    
    @Override
    public List<MedicalRecord> getPatientRecords(Long patientId) {
        return repo.findByPatientId(patientId);
    }
    
    @Override
    public MedicalRecord getRecordById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Record not found"));
    }
}
