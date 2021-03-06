package com.qnocks.universemanagesystem.service.impl;

import com.qnocks.universemanagesystem.domain.Ruler;
import com.qnocks.universemanagesystem.dto.RulerDto;
import com.qnocks.universemanagesystem.mapper.MapperUtil;
import com.qnocks.universemanagesystem.repository.RulerRepository;
import com.qnocks.universemanagesystem.service.RulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RulerServiceImpl implements RulerService {

    private final RulerRepository rulerRepository;

    private final MapperUtil mapper;

    @Override
    public RulerDto save(RulerDto rulerDto) {
        Ruler ruler = mapper.toEntity(rulerDto);
        Ruler savedRuler = rulerRepository.save(ruler);
        return mapper.toDto(savedRuler);
    }

    @Override
    public List<RulerDto> getFreeRulers() {
        return rulerRepository.findAllByPlanetsIsNull().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RulerDto> getTop10Youngest() {
        return rulerRepository.findTop10Youngest().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RulerDto> getAll() {
        return rulerRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
