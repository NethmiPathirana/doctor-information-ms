package com.doctors.information.service;

import com.doctors.information.dtos.request.DoctorAvailabilityDTO;
import com.doctors.information.entity.DoctorAvailability;

public interface DoctorAvailabilityService {
    DoctorAvailability addAvailability(Long doctorId, DoctorAvailabilityDTO availabilityDTO) throws RuntimeException;

    boolean deleteAvailability(Long id);
}
