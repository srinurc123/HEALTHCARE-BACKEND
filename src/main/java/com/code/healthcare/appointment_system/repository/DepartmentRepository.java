package com.code.healthcare.appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.healthcare.appointment_system.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
