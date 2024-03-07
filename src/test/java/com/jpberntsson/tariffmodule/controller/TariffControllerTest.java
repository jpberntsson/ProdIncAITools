package com.jpberntsson.tariffmodule.controller;

import com.jpberntsson.tariffmodule.domain.Tariff;
import com.jpberntsson.tariffmodule.repository.TariffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TariffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TariffRepository tariffRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        tariffRepository.deleteAll();
    }

    @Test
    public void testCreateTariff() throws Exception {
        Tariff tariff = new Tariff("1", "EUR"); // Adjust constructor based on your Tariff class

        mockMvc.perform(post("/api/tariffs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tariff)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testGetAllTariffs() throws Exception {
        // Pre-populate database if needed

        mockMvc.perform(get("/api/tariffs"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetTariffById() throws Exception {
        // Pre-populate database if needed
        Tariff tariff = tariffRepository.save(new Tariff("2", "USD")); // Adjust as necessary

        mockMvc.perform(get("/api/tariffs/{id}", tariff.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currency").value("USD"));
    }

    @Test
    public void testUpdateTariff() throws Exception {
        Tariff existingTariff = tariffRepository.save(new Tariff("3", "GBP")); // Adjust as necessary
        existingTariff.setCurrency("EUR"); // Update field for test

        mockMvc.perform(put("/api/tariffs/{id}", existingTariff.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existingTariff)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testDeleteTariff() throws Exception {
        Tariff tariffToBeDeleted = tariffRepository.save(new Tariff("4", "JPY")); // Adjust as necessary

        mockMvc.perform(delete("/api/tariffs/{id}", tariffToBeDeleted.getId()))
                .andExpect(status().isOk());

        // Optionally, verify that the tariff has been deleted
        mockMvc.perform(get("/api/tariffs/{id}", tariffToBeDeleted.getId()))
                .andExpect(status().isNotFound());
    }
}

