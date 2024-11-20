package com.doctors.information.repositories;
import com.doctors.information.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability,Long> {
}
