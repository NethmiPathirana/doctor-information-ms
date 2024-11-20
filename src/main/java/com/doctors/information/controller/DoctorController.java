package com.doctors.information.controller;
import com.doctors.information.dtos.request.DoctorDTO;
import com.doctors.information.dtos.response.DoctorDetailsResDTO;
import com.doctors.information.dtos.response.DoctorResDTO;
import com.doctors.information.entity.Doctor;
import com.doctors.information.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-sync")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/add-doctor")
    public ResponseEntity<DoctorResDTO> addDoctor(@RequestBody DoctorDTO doctorDTO) {
        DoctorResDTO savedDoctor = doctorService.saveDoctor(doctorDTO);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/update-doctor/{id}")
    public ResponseEntity<DoctorResDTO> editDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        DoctorResDTO updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
        if (updatedDoctor != null) {
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        boolean isDeleted = doctorService.deleteDoctor(id);
        if (isDeleted) {
            return new ResponseEntity<>("Doctor with ID " + id + " has been successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Doctor with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/get-doctor/{id}")
    public ResponseEntity<DoctorDetailsResDTO> getDoctorById(@PathVariable Long id) {
        DoctorDetailsResDTO doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
