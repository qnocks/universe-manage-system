package com.qnocks.universemanagesystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.service.impl.PlanetServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(PlanetRestController.class)
class PlanetRestControllerTest {

    @Value("${app.test.api.planets}")
    private String api;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonMapper;

    @MockBean
    private PlanetServiceImpl planetService;

    private List<PlanetDto> planetsDto;

    @BeforeEach
    void setup() {
        planetsDto = List.of(
                new PlanetDto(1L, "Planet1", null),
                new PlanetDto(2L, "Planet2", null),
                new PlanetDto(3L, "Planet3", null));
    }

    @AfterEach
    void tearDown() {
        planetsDto = null;
    }

    @Test
    @DisplayName("GET /api/v1/planets")
    void shouldReturnListPlanets() throws Exception {
        when(planetService.getAll()).thenReturn(planetsDto);

        mockMvc.perform(get(api))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonMapper.writeValueAsString(planetsDto)));
    }

    @Test
    @DisplayName("POST /api/v1/planets")
    void shouldSaveNewPlanet() throws Exception {
        PlanetDto planetDto = new PlanetDto(1L, "Planet1", null);

        when(planetService.save(planetDto)).thenReturn(planetDto);

        mockMvc.perform(
                post(api)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsBytes(planetDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonMapper.writeValueAsString(planetDto)));
    }

    @Test
    @DisplayName("PATCH /api/v1/planets")
    void shouldUpdatePlanet() throws Exception {
        PlanetDto toUpdate = new PlanetDto(1L, "Old Planet", null);
        PlanetDto expected = new PlanetDto(1L, "New Planet", null);

        when(planetService.update(toUpdate.getId(), toUpdate)).thenReturn(expected);

        mockMvc.perform(
                patch(api + "/{id}", toUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsBytes(toUpdate)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonMapper.writeValueAsString(expected)));
    }

    @Test
    @DisplayName("DELETE /api/v1/planets")
    void shouldDeletePlanet() throws Exception {
        PlanetDto toDelete = new PlanetDto(1L, "Planet1", null);

        mockMvc.perform(delete(api + "/{id}", toDelete.getId()))
                .andExpect(status().isNoContent());
    }
}