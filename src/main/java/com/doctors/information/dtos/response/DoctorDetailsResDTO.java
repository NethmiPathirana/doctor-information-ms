package com.doctors.information.dtos.response;

import com.doctors.information.entity.DoctorAvailability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDetailsResDTO {
    private String firstName;
    private String lastName;
    private String speciality;
    private String contactInfo;
    private List<DoctorAvailability> availability;
}
