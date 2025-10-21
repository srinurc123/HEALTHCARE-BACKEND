package com.code.healthcare.appointment_system.service;

import java.util.List;

public interface CacheService {
    void cacheDoctorAvailability(Long doctorId, Object slots);
    Object getDoctorAvailability(Long doctorId);
    void evictDoctorAvailability(Long doctorId);
}
