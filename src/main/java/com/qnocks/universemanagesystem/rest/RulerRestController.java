package com.qnocks.universemanagesystem.rest;

import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.dto.RulerDto;
import com.qnocks.universemanagesystem.service.RulerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rulers")
@RequiredArgsConstructor
@Api("CRUD operations with Rulers")
public class RulerRestController {

    private final RulerService rulerService;

    @GetMapping
    @ApiOperation(value = "Finds all Rulers", response = RulerDto.class)
    public ResponseEntity<List<RulerDto>> list() {
        return new ResponseEntity<>(rulerService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Saves Ruler", response = PlanetDto.class)
    public ResponseEntity<RulerDto> save(@RequestBody RulerDto rulerDto) {
        return new ResponseEntity<>(rulerService.save(rulerDto), HttpStatus.CREATED);
    }

    @GetMapping("free")
    @ApiOperation(value = "Finds all Rulers where there's no Planets to rule", response = RulerDto.class)
    public ResponseEntity<List<RulerDto>> getFreeRulers() {
        return new ResponseEntity<>(rulerService.getFreeRulers(), HttpStatus.OK);
    }

    @GetMapping("new")
    @ApiOperation(value = "Finds top 10 Rulers with youngest age", response = RulerDto.class)
    public ResponseEntity<List<RulerDto>> getYoungestRulers() {
        return new ResponseEntity<>(rulerService.getTop10Youngest(), HttpStatus.OK);
    }
}
