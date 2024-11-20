package com.doctors.information.service.impl;
import com.doctors.information.dtos.request.DoctorDTO;
import com.doctors.information.dtos.response.DoctorDetailsResDTO;
import com.doctors.information.dtos.response.DoctorResDTO;
import com.doctors.information.entity.Doctor;
import com.doctors.information.repositories.DoctorRepository;
import com.doctors.information.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorResDTO saveDoctor(DoctorDTO doctorDTO) {

        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setSpeciality(doctorDTO.getSpeciality());
        doctor.setContactInfo(doctorDTO.getContactInfo());

        Doctor doctor1 = doctorRepository.save(doctor);
        DoctorResDTO doctorResDTO = new DoctorResDTO();
        doctorResDTO.setFirstName(doctor1.getFirstName());
        doctorResDTO.setLastName(doctor1.getLastName());
        doctorResDTO.setSpeciality(doctor1.getSpeciality());
        doctorResDTO.setContactInfo(doctor1.getContactInfo());
        return doctorResDTO;
    }

    @Override
    public DoctorResDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);

        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            doctor.setFirstName(doctorDTO.getFirstName());
            doctor.setLastName(doctorDTO.getLastName());
            doctor.setSpeciality(doctorDTO.getSpeciality());
            doctor.setContactInfo(doctorDTO.getContactInfo());

            DoctorResDTO doctorResDTO = new DoctorResDTO();
            doctorResDTO.setFirstName(doctorDTO.getFirstName());
            doctorResDTO.setLastName(doctorDTO.getLastName());
            doctorResDTO.setSpeciality(doctorDTO.getSpeciality());
            doctorResDTO.setContactInfo(doctorDTO.getContactInfo());
            return doctorResDTO;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public DoctorDetailsResDTO getDoctorById(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        return doctorOptional.map(this::mapToDoctorResDTO).orElse(null);
    }

    private DoctorDetailsResDTO mapToDoctorResDTO(Doctor doctor) {
        DoctorDetailsResDTO doctorDetailsResDTO = new DoctorDetailsResDTO();
        doctorDetailsResDTO.setFirstName(doctor.getFirstName());
        doctorDetailsResDTO.setLastName(doctor.getLastName());
        doctorDetailsResDTO.setSpeciality(doctor.getSpeciality());
        doctorDetailsResDTO.setContactInfo(doctor.getContactInfo());
        doctorDetailsResDTO.setAvailability(doctor.getAvailability());
        return doctorDetailsResDTO;
    }
}
