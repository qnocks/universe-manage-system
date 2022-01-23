package com.qnocks.universemanagesystem.service;

import com.qnocks.universemanagesystem.dto.RulerDto;

import java.util.List;

public interface RulerService {

    List<RulerDto> getAll();

    RulerDto save(RulerDto rulerDto);

    List<RulerDto> getFreeRulers();

    List<RulerDto> getTop10Youngest();
}
