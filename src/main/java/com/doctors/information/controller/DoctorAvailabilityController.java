package com.doctors.information.controller;

import com.doctors.information.dtos.request.DoctorAvailabilityDTO;
import com.doctors.information.entity.DoctorAvailability;
import com.doctors.information.service.DoctorAvailabilityService;
import com.doctors.information.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health-sync")
public class DoctorAvailabilityController {
    private final DoctorAvailabilityService doctorAvailabilityService;

    @Autowired
    public DoctorAvailabilityController(DoctorAvailabilityService doctorAvailabilityService) {
        this.doctorAvailabilityService = doctorAvailabilityService;
    }

    @PostMapping("/doctors/{doctorId}/add-availability")
    public ResponseEntity<Object> addAvailability(
            @PathVariable Long doctorId,
            @RequestBody DoctorAvailabilityDTO availabilityDTO) {
        try {
            DoctorAvailability savedAvailability = doctorAvailabilityService.addAvailability(doctorId, availabilityDTO);
            return new ResponseEntity<>(savedAvailability, HttpStatus.CREATED);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete availability by ID
    @DeleteMapping("/delete-availability/{id}")
    public ResponseEntity<String> deleteAvailability(@PathVariable Long id) {
        boolean isDeleted = doctorAvailabilityService.deleteAvailability(id);

        if (isDeleted) {
            return new ResponseEntity<>("Availability record with ID " + id + " has been successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Availability record with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
