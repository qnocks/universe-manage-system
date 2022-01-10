package com.qnocks.universemanagesystem.mapper;

import com.qnocks.universemanagesystem.domain.Planet;
import com.qnocks.universemanagesystem.dto.PlanetDto;

public interface PlanetMapper {

    PlanetDto toDto(Planet planet);

    Planet toEntity(PlanetDto ruler);
}
