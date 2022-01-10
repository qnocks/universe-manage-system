package com.qnocks.universemanagesystem.mapper;

import com.qnocks.universemanagesystem.domain.Planet;
import com.qnocks.universemanagesystem.domain.Ruler;
import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.dto.RulerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperUtil implements PlanetMapper, RulerMapper {

    private final ModelMapper mapper;

    @Override
    public PlanetDto toDto(Planet planet) {
        return mapper.map(planet, PlanetDto.class);
    }

    @Override
    public Planet toEntity(PlanetDto planetDto) {
        return mapper.map(planetDto, Planet.class);
    }

    @Override
    public RulerDto toDto(Ruler ruler) {
        return mapper.map(ruler, RulerDto.class);
    }

    @Override
    public Ruler toEntity(RulerDto rulerDto) {
        return mapper.map(rulerDto, Ruler.class);
    }
}
