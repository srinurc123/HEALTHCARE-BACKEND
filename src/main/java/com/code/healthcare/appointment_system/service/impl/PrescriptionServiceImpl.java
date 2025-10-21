package com.code.healthcare.appointment_system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import com.code.healthcare.appointment_system.service.PrescriptionService;
import com.code.healthcare.appointment_system.model.*;
import com.code.healthcare.appointment_system.repository.*;
import com.code.healthcare.appointment_system.dto.request.PrescriptionRequest;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository repo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final AppointmentRepository appointmentRepo;
    
    @Override
    public Prescription createPrescription(PrescriptionRequest request) {
        Patient patient = null;
        Doctor doctor = null;

        if (request.getPatientId() != null) {
            patient = patientRepo.findById(request.getPatientId()).orElse(null);
        }
        if (request.getDoctorId() != null) {
            doctor = doctorRepo.findById(request.getDoctorId()).orElse(null);
        }

        if ((patient == null || doctor == null) && request.getAppointmentId() != null) {
            Appointment appt = appointmentRepo.findById(request.getAppointmentId()).orElse(null);
            if (appt != null) {
                if (patient == null) patient = appt.getPatient();
                if (doctor == null) doctor = appt.getDoctor();
            }
        }

        if (patient == null) throw new RuntimeException("Patient not found");
        if (doctor == null) throw new RuntimeException("Doctor not found");
        
        Prescription prescription = new Prescription();
        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        
        if (request.getAppointmentId() != null) {
            appointmentRepo.findById(request.getAppointmentId()).ifPresentOrElse(
                (appointment) -> prescription.setAppointment(appointment),
                () -> log.warn("Requested appointment id {} not found - creating prescription without linking appointment", request.getAppointmentId())
            );
        }
        
        prescription.setPrescriptionDate(request.getPrescriptionDate());
        prescription.setMedications(request.getMedications());
        prescription.setDosage(request.getDosage());
        prescription.setInstructions(request.getInstructions());
        prescription.setDurationDays(request.getDurationDays());
        
        return repo.save(prescription);
    }
    
    @Override
    public List<Prescription> getPatientPrescriptions(Long patientId) {
        return repo.findByPatientId(patientId);
    }
    
    @Override
    public Prescription getPrescriptionById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Prescription not found"));
    }
}
