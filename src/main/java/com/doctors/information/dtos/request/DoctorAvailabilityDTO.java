package com.doctors.information.dtos.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DoctorAvailabilityDTO {
    private LocalDate availableDate;
    private LocalTime availableTime;
    private int maxPatientCount;
}
