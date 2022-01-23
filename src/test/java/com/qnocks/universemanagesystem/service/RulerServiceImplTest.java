package com.qnocks.universemanagesystem.service;

import com.qnocks.universemanagesystem.domain.Planet;
import com.qnocks.universemanagesystem.domain.Ruler;
import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.dto.RulerDto;
import com.qnocks.universemanagesystem.mapper.MapperUtil;
import com.qnocks.universemanagesystem.repository.RulerRepository;
import com.qnocks.universemanagesystem.service.impl.RulerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RulerServiceImplTest {

    @InjectMocks
    private RulerServiceImpl underTest;

    @Mock
    private RulerRepository rulerRepository;

    @Mock
    private MapperUtil mapper;

    private List<Ruler> testRulers;

    @BeforeEach
    void setup() {
        testRulers = List.of(
                new Ruler(1L, "Ruler1", 1, Set.of(new Planet())),
                new Ruler(2L, "Ruler2", 2, null),
                new Ruler(3L, "Ruler3", 3, null),
                new Ruler(4L, "Ruler4", 4, null),
                new Ruler(5L, "Ruler5", 5, null),
                new Ruler(6L, "Ruler6", 6, null),
                new Ruler(7L, "Ruler7", 7, null),
                new Ruler(8L, "Ruler8", 8, null),
                new Ruler(9L, "Ruler9", 9, null),
                new Ruler(10L, "Ruler10", 10, null),
                new Ruler(11L, "Ruler11", 11, null),
                new Ruler(12L, "Ruler12", 12, null),
                new Ruler(13L, "Ruler13", 13,null),
                new Ruler(14L, "Ruler14", 14, Set.of(new Planet()))
        );
    }

    @AfterEach
    void tearDown() {
        testRulers = null;
    }

    @Test
    @DisplayName("Should save new Ruler")
    void shouldSaveNewRuler() {
        // given
        Ruler expected = new Ruler(null, "Ruler1", 10000, Set.of(new Planet()));
        RulerDto expectedDto = new RulerDto(null, "Ruler1", 10000, Set.of(new PlanetDto()));

        given(mapper.toEntity(expectedDto)).willReturn(expected);
        given(mapper.toDto(expected)).willReturn(expectedDto);
        given(rulerRepository.save(expected)).willReturn(expected);

        underTest.save(expectedDto);

        ArgumentCaptor<Ruler> captor = ArgumentCaptor.forClass(Ruler.class);
        verify(rulerRepository).save(captor.capture());
        Ruler actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should get all rulers")
    void shouldGetAllRulers() {
        when(rulerRepository.findAll()).thenReturn(testRulers);

        List<RulerDto> actual = underTest.getAll();

        assertThat(actual.size()).isEqualTo(testRulers.size());
    }

    @Test
    @DisplayName("Should get free rulers")
    void shouldGetAllFreeRulers() {
        when(rulerRepository.findAllByPlanetsIsNull()).thenReturn(testRulers.stream()
                .filter(r -> r.getPlanets() == null)
                .collect(Collectors.toList()));

        underTest.getFreeRulers();

        verify(rulerRepository, times(1)).findAllByPlanetsIsNull();
    }

    @Test
    @DisplayName("Should get top 10 youngest rulers")
    void shouldGetTop10YoungestRulers() {
        List<Ruler> expected = testRulers.stream()
                .sorted(Comparator.comparing(Ruler::getAge))
                .limit(10)
                .collect(Collectors.toList());

        when(rulerRepository.findTop10Youngest()).thenReturn(expected);

        List<RulerDto> actual = underTest.getTop10Youngest();

        assertThat(actual).isEqualTo(expected.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
    }
}
