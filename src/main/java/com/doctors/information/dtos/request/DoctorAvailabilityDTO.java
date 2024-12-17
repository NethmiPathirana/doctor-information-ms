package com.doctors.information.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAvailabilityDTO {
    private LocalDate availableDate;
    private LocalTime availableTime;
    private int maxPatientCount;
}
