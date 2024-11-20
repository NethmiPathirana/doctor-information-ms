package com.doctors.information.dtos.response;

import com.doctors.information.entity.DoctorAvailability;
import lombok.Data;

import java.util.List;

@Data
public class DoctorDetailsResDTO {
    private String firstName;
    private String lastName;
    private String speciality;
    private String contactInfo;
    private List<DoctorAvailability> availability;
}
