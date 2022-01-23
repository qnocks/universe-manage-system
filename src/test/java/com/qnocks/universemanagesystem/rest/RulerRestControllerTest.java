package com.qnocks.universemanagesystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.dto.RulerDto;
import com.qnocks.universemanagesystem.service.impl.RulerServiceImpl;
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

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(RulerRestController.class)
class RulerRestControllerTest {

    @Value("${app.test.api.rulers}")
    private String api;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonMapper;

    @MockBean
    private RulerServiceImpl rulerService;

    private List<RulerDto> rulersDto;

    @BeforeEach
    void setup() {
        rulersDto = List.of(
                new RulerDto(1L, "Ruler1", 1, Set.of(new PlanetDto())),
                new RulerDto(2L, "Ruler2", 2, Set.of()),
                new RulerDto(3L, "Ruler3", 3, Set.of(new PlanetDto())),
                new RulerDto(4L, "Ruler4", 4, Set.of()),
                new RulerDto(5L, "Ruler5", 5, Set.of()),
                new RulerDto(6L, "Ruler6", 6, Set.of(new PlanetDto())),
                new RulerDto(7L, "Ruler7", 7, Set.of()),
                new RulerDto(8L, "Ruler8", 8, Set.of()),
                new RulerDto(9L, "Ruler9", 5, Set.of()),
                new RulerDto(10L, "Ruler10", 10, Set.of()),
                new RulerDto(11L, "Ruler11", 11, Set.of(new PlanetDto())));
    }

    @AfterEach
    void tearDown() {
        rulersDto = null;
    }

    @Test
    @DisplayName("POST /api/v1/rulers")
    void shouldSaveNewRuler() throws Exception {
        RulerDto rulerDto = new RulerDto(1L, "Ruler1", 111, null);

        when(rulerService.save(rulerDto)).thenReturn(rulerDto);

        mockMvc.perform(
                post(api)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsBytes(rulerDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonMapper.writeValueAsString(rulerDto)));
    }

    @Test
    @DisplayName("GET /api/v1/rulers")
    void shouldReturnListRulers() throws Exception {
        when(rulerService.getAll()).thenReturn(rulersDto);

        mockMvc.perform(get(api))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonMapper.writeValueAsString(rulersDto)));
    }

    @Test
    @DisplayName("GET /api/v1/rulers/free")
    void shouldReturnFreeRulers() throws Exception {
        List<RulerDto> expected = rulersDto.stream()
                .filter(ruler -> ruler.getPlanets().size() == 0)
                .collect(Collectors.toList());

        when(rulerService.getFreeRulers()).thenReturn(expected);

        mockMvc.perform(get(api + "/free"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonMapper.writeValueAsString(expected)));
    }

    @Test
    @DisplayName("GET /api/v1/rulers/new")
    void getYoungestRulers() throws Exception {
        List<RulerDto> expected = rulersDto.stream()
                .sorted(Comparator.comparing(RulerDto::getAge))
                .limit(10)
                .collect(Collectors.toList());

        when(rulerService.getTop10Youngest()).thenReturn(expected);

        mockMvc.perform(get(api + "/new"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonMapper.writeValueAsString(expected)));
    }
}