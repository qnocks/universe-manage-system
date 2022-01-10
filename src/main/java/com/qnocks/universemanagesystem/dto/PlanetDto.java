package com.qnocks.universemanagesystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PlanetDto {

    private Long id;

    private String name;

    @JsonIgnoreProperties("planets")
    private RulerDto ruler;
}
