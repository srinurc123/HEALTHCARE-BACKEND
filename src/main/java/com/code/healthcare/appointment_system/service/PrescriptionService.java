package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.model.Prescription;
import com.code.healthcare.appointment_system.dto.request.PrescriptionRequest;
import java.util.List;

public interface PrescriptionService {
    Prescription createPrescription(PrescriptionRequest request);
    List<Prescription> getPatientPrescriptions(Long patientId);
    Prescription getPrescriptionById(Long id);
}
