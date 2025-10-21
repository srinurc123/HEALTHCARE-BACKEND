package com.code.healthcare.appointment_system.service;

import com.code.healthcare.appointment_system.model.AvailabilitySlot;
import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilityService {
    List<AvailabilitySlot> getDoctorAvailableSlots(Long doctorId, LocalDateTime date);
    AvailabilitySlot blockSlot(Long slotId);
    AvailabilitySlot markSlotBooked(Long slotId);
}
