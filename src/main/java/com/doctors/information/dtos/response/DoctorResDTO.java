package com.doctors.information.dtos.response;

import lombok.Data;

@Data
public class DoctorResDTO {
    private String firstName;
    private String lastName;
    private String speciality;
    private String contactInfo;
}
