package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.model.MedicalRecord;
import com.code.healthcare.appointment_system.dto.request.MedicalRecordRequest;
import java.util.List;

public interface MedicalRecordService {
    MedicalRecord createRecord(MedicalRecordRequest request);
    MedicalRecord updateRecord(Long id, MedicalRecordRequest request);
    List<MedicalRecord> getPatientRecords(Long patientId);
    MedicalRecord getRecordById(Long id);
}
