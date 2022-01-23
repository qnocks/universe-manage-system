package com.qnocks.universemanagesystem.service;

import com.qnocks.universemanagesystem.domain.Planet;
import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.mapper.MapperUtil;
import com.qnocks.universemanagesystem.repository.PlanetRepository;
import com.qnocks.universemanagesystem.service.impl.PlanetServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanetServiceImplTest {

    @InjectMocks
    private PlanetServiceImpl underTest;

    @Mock
    private PlanetRepository planetRepository;

    @Mock
    private MapperUtil mapper;

    private List<Planet> testPlanets;

    @BeforeEach
    void setup() {
        testPlanets = List.of(
                new Planet(1L, "Planet1", null),
                new Planet(2L, "Planet2", null),
                new Planet(3L, "Planet3", null),
                new Planet(3L, "Planet4", null),
                new Planet(5L, "Planet5", null));
    }

    @AfterEach
    void tearDown() {
        testPlanets = null;
    }

    @Test
    @DisplayName("Should save new Planet")
    void shouldSaveNewPlanet() {
        Planet expected = new Planet(0L, "Planet0", null);
        PlanetDto expectedDto = new PlanetDto(0L, "Planet0", null);

        given(mapper.toEntity(expectedDto)).willReturn(expected);
        given(mapper.toDto(expected)).willReturn(expectedDto);
        given(planetRepository.save(expected)).willReturn(expected);

        underTest.save(expectedDto);

        ArgumentCaptor<Planet> captor = ArgumentCaptor.forClass(Planet.class);
        verify(planetRepository).save(captor.capture());
        Planet actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should get all Planets")
    void shouldGetAllPlanets() {
        when(planetRepository.findAll()).thenReturn(testPlanets);

        List<PlanetDto> actual = underTest.getAll();

        assertThat(actual.size()).isEqualTo(testPlanets.size());
    }

    @Test
    @DisplayName("Should update Planet")
    void shouldUpdatePlanet() {
        Planet toUpdate = new Planet(1L, "Old Planet", null);
        Planet expected = new Planet(1L, "New Planet", null);
        PlanetDto expectedDto = new PlanetDto(1L, "New Planet", null);

        when(mapper.toEntity(expectedDto)).thenReturn(expected);
        when(mapper.toDto(expected)).thenReturn(expectedDto);
        when(planetRepository.findById(expectedDto.getId())).thenReturn(Optional.of(toUpdate));
        when(planetRepository.save(expected)).thenReturn(expected);

        PlanetDto actual = underTest.update(expectedDto.getId(), expectedDto);

        verify(planetRepository, times(1)).save(expected);

        assertThat(actual).isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("Should delete Planet")
    void shouldDeletePlanet() {
        Planet toDelete = new Planet(1L, "To Delete", null);

        underTest.delete(toDelete.getId());

        verify(planetRepository, times(1)).deleteById(toDelete.getId());
    }
}
