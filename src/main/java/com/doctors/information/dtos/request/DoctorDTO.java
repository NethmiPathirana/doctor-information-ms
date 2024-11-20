package com.doctors.information.dtos.request;

import lombok.Data;

@Data
public class DoctorDTO {
    private String firstName;
    private String lastName;
    private String speciality;
    private String contactInfo;
}
