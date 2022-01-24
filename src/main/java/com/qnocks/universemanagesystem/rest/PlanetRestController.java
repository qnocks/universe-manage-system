package com.qnocks.universemanagesystem.rest;

import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.service.PlanetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planets")
@RequiredArgsConstructor
@Api("CRUD operations with Planets")
public class PlanetRestController {

    private final PlanetService planetService;

    @GetMapping
    @ApiOperation(value = "Finds all Planets", response = PlanetDto.class)
    public ResponseEntity<List<PlanetDto>> list() {
        return new ResponseEntity<>(planetService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Saves Planet", response = PlanetDto.class)
    public ResponseEntity<PlanetDto> save(@RequestBody PlanetDto planetDto) {
        return new ResponseEntity<>(planetService.save(planetDto), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    @ApiOperation(value = "Update existing Planet",
            notes = "Provide an id to update the Planet",
            response = PlanetDto.class)
    public ResponseEntity<PlanetDto> patch(@PathVariable Long id, @RequestBody PlanetDto planetDto) {
        return new ResponseEntity<>(planetService.update(id, planetDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletes specific Planet", notes = "Provide an id to delete the Planet")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planetService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
