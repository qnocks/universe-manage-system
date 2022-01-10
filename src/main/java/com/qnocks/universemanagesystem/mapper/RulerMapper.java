package com.qnocks.universemanagesystem.mapper;

import com.qnocks.universemanagesystem.domain.Ruler;
import com.qnocks.universemanagesystem.dto.RulerDto;

public interface RulerMapper {

    RulerDto toDto(Ruler ruler);

    Ruler toEntity(RulerDto ruler);
}
