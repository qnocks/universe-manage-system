package com.qnocks.universemanagesystem.service;

import com.qnocks.universemanagesystem.dto.PlanetDto;

import java.util.List;

public interface PlanetService {

    List<PlanetDto> getAll();

    PlanetDto save(PlanetDto planetDto);

    PlanetDto update(Long id, PlanetDto planetDto);

    void delete(Long id);
}
