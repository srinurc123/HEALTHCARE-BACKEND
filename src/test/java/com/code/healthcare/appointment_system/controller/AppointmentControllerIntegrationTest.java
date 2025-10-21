package com.code.healthcare.appointment_system.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testBookAppointment_Unauthorized() throws Exception {
        mockMvc.perform(post("/api/v1/appointments/book")
            .param("patientId", "1")
            .param("doctorId", "1")
            .param("time", "2025-10-20T10:00:00")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
    }
}
