package com.qnocks.universemanagesystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("Ruler")
public class RulerDto {

    private Long id;

    @ApiModelProperty(value = "Ruler name",  dataType = "String", example = "Ivan the Terrible")
    private String name;

    @ApiModelProperty(value = "Ruler ag1",  dataType = "Integer", example = "7000002")
    private Integer age;

    @JsonIgnoreProperties("ruler")
    @ApiModelProperty(value = "List of Planets this Ruler owns")
    private Set<PlanetDto> planets = new HashSet<>();
}
