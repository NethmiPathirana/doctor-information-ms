package com.doctors.information.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String speciality;
    private String contactInfo;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<DoctorAvailability> availability;
}
