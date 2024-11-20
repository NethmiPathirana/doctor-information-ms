package com.doctors.information.service.impl;

import com.doctors.information.dtos.request.DoctorAvailabilityDTO;
import com.doctors.information.entity.Doctor;
import com.doctors.information.entity.DoctorAvailability;
import com.doctors.information.repositories.DoctorAvailabilityRepository;
import com.doctors.information.repositories.DoctorRepository;
import com.doctors.information.service.DoctorAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorAvailabilityImpl implements DoctorAvailabilityService {
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorAvailabilityImpl(DoctorAvailabilityRepository doctorAvailabilityRepository,
                                  DoctorRepository doctorRepository) {
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorAvailability addAvailability(Long doctorId, DoctorAvailabilityDTO availabilityDTO) throws RuntimeException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);

        if (doctorOptional.isPresent()) {
            DoctorAvailability availability = new DoctorAvailability();
            availability.setDoctor(doctorOptional.get());
            availability.setAvailableDate(availabilityDTO.getAvailableDate());
            availability.setAvailableTime(availabilityDTO.getAvailableTime());
            availability.setMaxPatientCount(availabilityDTO.getMaxPatientCount());

            return doctorAvailabilityRepository.save(availability);
        } else {
            throw new RuntimeException("Doctor with ID " + doctorId + " not found.");
        }
    }

    @Override
    public boolean deleteAvailability(Long id) {
        if (doctorAvailabilityRepository.existsById(id)) {
            doctorAvailabilityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
