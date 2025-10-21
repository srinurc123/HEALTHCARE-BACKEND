package com.code.healthcare.appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.healthcare.appointment_system.model.AvailabilitySlot;
import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Long> {
    List<AvailabilitySlot> findByDoctorIdAndStartTimeBetween(
        Long doctorId, LocalDateTime start, LocalDateTime end);
    
    List<AvailabilitySlot> findByDoctorIdAndStatusAndStartTimeBetween(
        Long doctorId, AvailabilitySlot.SlotStatus status, 
        LocalDateTime start, LocalDateTime end);
}
