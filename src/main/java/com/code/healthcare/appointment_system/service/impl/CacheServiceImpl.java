package com.code.healthcare.appointment_system.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.code.healthcare.appointment_system.service.CacheService;
import lombok.RequiredArgsConstructor;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String AVAILABILITY_PREFIX = "doctor:availability:";

    @Override
    public void cacheDoctorAvailability(Long doctorId, Object slots) {
        String key = AVAILABILITY_PREFIX + doctorId;
        redisTemplate.opsForValue().set(key, slots, 30, TimeUnit.MINUTES);
    }

    @Override
    public Object getDoctorAvailability(Long doctorId) {
        String key = AVAILABILITY_PREFIX + doctorId;
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void evictDoctorAvailability(Long doctorId) {
        String key = AVAILABILITY_PREFIX + doctorId;
        redisTemplate.delete(key);
    }
}
