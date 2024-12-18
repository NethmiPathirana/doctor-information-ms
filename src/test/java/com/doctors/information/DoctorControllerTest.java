package com.doctors.information;

import com.doctors.information.controller.DoctorController;
import com.doctors.information.dtos.request.DoctorDTO;
import com.doctors.information.dtos.response.DoctorDetailsResDTO;
import com.doctors.information.dtos.response.DoctorResDTO;
import com.doctors.information.repositories.DoctorAvailabilityRepository;
import com.doctors.information.repositories.DoctorRepository;
import com.doctors.information.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddDoctor() throws Exception {

        DoctorDTO doctorDTO = new DoctorDTO("John", "Doe", "Cardiology", "123-456-7890");
        DoctorResDTO doctorResDTO = new DoctorResDTO("John", "Doe", "Cardiology", "123-456-7890");Mockito.when(doctorService.saveDoctor(Mockito.any(DoctorDTO.class))).thenReturn(doctorResDTO);

        // Act & Assert
        mockMvc.perform(post("/health-sync/add-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.speciality").value("Cardiology"))
                .andExpect(jsonPath("$.contactInfo").value("123-456-7890"));
    }

    @Test
    public void testEditDoctor_NotFound() throws Exception {
        Long doctorId = 1L;
        DoctorDTO doctorDTO = new DoctorDTO("John", "Doe", "Cardiology", "123-456-7890");

        Mockito.when(doctorService.updateDoctor(doctorId, doctorDTO)).thenReturn(null);

        mockMvc.perform(put("/health-sync/update-doctor/{id}", doctorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteDoctor() throws Exception {
        Long doctorId = 1L;

        Mockito.when(doctorService.deleteDoctor(doctorId)).thenReturn(true);

        mockMvc.perform(delete("/health-sync/delete-doctor/{id}", doctorId))
                .andExpect(status().isOk())
                .andExpect(content().string("Doctor with ID 1 has been successfully deleted."));
    }

    @Test
    public void testDeleteDoctor_NotFound() throws Exception {
        Long doctorId = 1L;

        Mockito.when(doctorService.deleteDoctor(doctorId)).thenReturn(false);

        mockMvc.perform(delete("/health-sync/delete-doctor/{id}", doctorId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Doctor with ID 1 not found."));
    }

    @Test
    public void testGetDoctorById() throws Exception {
        Long doctorId = 1L;
        DoctorDetailsResDTO doctor = new DoctorDetailsResDTO("John", "Doe", "Cardiology", "123-456-7890", List.of());

        Mockito.when(doctorService.getDoctorById(doctorId)).thenReturn(doctor);

        mockMvc.perform(get("/health-sync/get-doctor/{id}", doctorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.speciality").value("Cardiology"))
                .andExpect(jsonPath("$.contactInfo").value("123-456-7890"));
    }

    @Test
    public void testGetDoctorById_NotFound() throws Exception {
        Long doctorId = 1L;

        Mockito.when(doctorService.getDoctorById(doctorId)).thenReturn(null);

        mockMvc.perform(get("/health-sync/get-doctor/{id}", doctorId))
                .andExpect(status().isNotFound());
    }
}

