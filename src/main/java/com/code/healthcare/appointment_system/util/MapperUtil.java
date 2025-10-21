package com.code.healthcare.appointment_system.util;

import com.code.healthcare.appointment_system.dto.response.*;
import com.code.healthcare.appointment_system.model.*;

public class MapperUtil {

    public static DoctorResponse toDoctorResponse(Doctor doctor) {
        return new DoctorResponse(
            doctor.getId(),
            doctor.getFirstName(),
            doctor.getLastName(),
            doctor.getSpecialization(),
            doctor.getEmail()
        );
    }

    public static PatientResponse toPatientResponse(Patient patient) {
        return new PatientResponse(
            patient.getId(),
            patient.getFirstName(),
            patient.getLastName(),
            patient.getEmail(),
            patient.getPhone()
        );
    }

    public static AppointmentResponse toAppointmentResponse(Appointment appt) {
        return new AppointmentResponse(
            appt.getId(),
            appt.getAppointmentTime(),
            appt.getStatus().name(),
            appt.getPatient().getFirstName() + " " + appt.getPatient().getLastName(),
            "Dr. " + appt.getDoctor().getFirstName() + " " + appt.getDoctor().getLastName(),
            appt.getDoctor().getSpecialization()
        );
    }
}
