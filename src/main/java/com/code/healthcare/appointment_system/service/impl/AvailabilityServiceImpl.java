package com.code.healthcare.appointment_system.service.impl;

import org.springframework.stereotype.Service;
import com.code.healthcare.appointment_system.service.AvailabilityService;
import com.code.healthcare.appointment_system.model.AvailabilitySlot;
import com.code.healthcare.appointment_system.repository.AvailabilitySlotRepository;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilitySlotRepository repo;

    @Override
    public List<AvailabilitySlot> getDoctorAvailableSlots(Long doctorId, LocalDateTime date) {
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = date.toLocalDate().atTime(23, 59);
        return repo.findByDoctorIdAndStatusAndStartTimeBetween(
            doctorId, AvailabilitySlot.SlotStatus.AVAILABLE, startOfDay, endOfDay);
    }

    @Override
    public AvailabilitySlot blockSlot(Long slotId) {
        AvailabilitySlot slot = repo.findById(slotId)
            .orElseThrow(() -> new RuntimeException("Slot not found"));
        slot.setStatus(AvailabilitySlot.SlotStatus.BLOCKED);
        return repo.save(slot);
    }

    @Override
    public AvailabilitySlot markSlotBooked(Long slotId) {
        AvailabilitySlot slot = repo.findById(slotId)
            .orElseThrow(() -> new RuntimeException("Slot not found"));
        slot.setStatus(AvailabilitySlot.SlotStatus.BOOKED);
        return repo.save(slot);
    }
}
