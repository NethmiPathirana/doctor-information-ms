package com.doctors.information.service;

import com.doctors.information.dtos.request.DoctorDTO;
import com.doctors.information.dtos.response.DoctorDetailsResDTO;
import com.doctors.information.dtos.response.DoctorResDTO;
import com.doctors.information.entity.Doctor;

import java.util.List;

public interface DoctorService {
    DoctorResDTO saveDoctor(DoctorDTO doctorDTO);

    DoctorResDTO updateDoctor(Long id, DoctorDTO doctorDTO);

    boolean deleteDoctor(Long id);

    List<Doctor> getAllDoctors();

    DoctorDetailsResDTO getDoctorById(Long id);
}
