package com.qnocks.universemanagesystem.service.impl;

import com.qnocks.universemanagesystem.domain.Planet;
import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.exception.ResourceNotFoundException;
import com.qnocks.universemanagesystem.mapper.MapperUtil;
import com.qnocks.universemanagesystem.repository.PlanetRepository;
import com.qnocks.universemanagesystem.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;

    private final MapperUtil mapper;

    @Override
    public List<PlanetDto> getAll() {
        return planetRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlanetDto save(PlanetDto planetDto) {
        Planet planet = mapper.toEntity(planetDto);
        Planet savedPlanet = planetRepository.save(planet);
        return mapper.toDto(savedPlanet);
    }

    @Override
    public PlanetDto update(Long id, PlanetDto planetDto) {
        Planet planet = mapper.toEntity(planetDto);
        Planet existingPlanet = planetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cannot find Planet with id: %d", id)));

        BeanUtils.copyProperties(planet, existingPlanet, "id");

        planetRepository.save(existingPlanet);

        return mapper.toDto(existingPlanet);
    }

    @Override
    public void delete(Long id) {
        planetRepository.deleteById(id);
    }
}
