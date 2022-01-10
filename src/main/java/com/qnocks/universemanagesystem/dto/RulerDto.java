package com.qnocks.universemanagesystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RulerDto {

    private Long id;

    private String name;

    private Integer age;

    @JsonIgnoreProperties("ruler")
    private Set<PlanetDto> planets = new HashSet<>();
}
