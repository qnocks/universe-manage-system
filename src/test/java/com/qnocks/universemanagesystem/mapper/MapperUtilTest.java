package com.qnocks.universemanagesystem.mapper;

import com.qnocks.universemanagesystem.domain.Planet;
import com.qnocks.universemanagesystem.domain.Ruler;
import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.dto.RulerDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MapperUtilTest {

    private final ModelMapper mapper = new ModelMapper();

    private Planet planet;

    private PlanetDto planetDto;

    private Ruler ruler;

    private RulerDto rulerDto;

    @BeforeEach
    void setup() {
        planet = new Planet();
        planet.setId(1L);
        planet.setName("Planet1");
        planet.setRuler(ruler);

        ruler = new Ruler();
        ruler.setId(1L);
        ruler.setName("Ruler1");
        ruler.setAge(111);
        ruler.setPlanets(Set.of(planet));

        planetDto = new PlanetDto();
        planetDto.setId(1L);
        planetDto.setName("Planet1");
        planetDto.setRuler(rulerDto);

        rulerDto = new RulerDto();
        rulerDto.setId(1L);
        rulerDto.setName("Ruler1");
        rulerDto.setAge(111);
        rulerDto.setPlanets(Set.of(planetDto));
    }

    @AfterEach
    void tearDown() {
        planet = null;
        planetDto = null;
        ruler = null;
        rulerDto = null;
    }

    @Test
    @DisplayName("Should map to planet dto")
    void shouldMapToPlanetDto() {
        PlanetDto actual = mapper.map(planet, PlanetDto.class);

        assertThat(actual).isEqualTo(planetDto);
    }

    @Test
    @DisplayName("Should map to planet entity")
    void shouldMapToPlanetEntity() {
        Planet actual = mapper.map(planetDto, Planet.class);

        assertThat(actual).isEqualTo(planet);
    }

    @Test
    @DisplayName("Should map to ruler dto")
    void shouldMapToRulerDto() {
        RulerDto actual = mapper.map(ruler, RulerDto.class);

        assertThat(actual).isEqualTo(rulerDto);
    }

    @Test
    @DisplayName("Should map to ruler entity")
    void shouldMapToRulerEntity() {
        Ruler actual = mapper.map(rulerDto, Ruler.class);

        assertThat(actual).isEqualTo(ruler);
    }
}