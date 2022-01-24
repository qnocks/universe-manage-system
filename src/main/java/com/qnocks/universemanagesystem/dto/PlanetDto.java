package com.qnocks.universemanagesystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ApiModel("Planet")
public class PlanetDto {

    private Long id;

    @ApiModelProperty(value = "Planet name", dataType = "String", example = "Earth")
    private String name;

    @JsonIgnoreProperties("planets")
    @ApiModelProperty(value = "Ruler who owns this Planet")
    private RulerDto ruler;
}
