package com.doctors.information.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResDTO {
    private String firstName;
    private String lastName;
    private String speciality;
    private String contactInfo;
}
