package com.qnocks.universemanagesystem.rest;

import com.qnocks.universemanagesystem.dto.PlanetDto;
import com.qnocks.universemanagesystem.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planets")
@RequiredArgsConstructor
public class PlanetRestController {

    private final PlanetService planetService;

    @GetMapping
    public ResponseEntity<List<PlanetDto>> list() {
        return new ResponseEntity<>(planetService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanetDto> save(@RequestBody PlanetDto planetDto) {
        return new ResponseEntity<>(planetService.save(planetDto), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<PlanetDto> patch(@PathVariable Long id, @RequestBody PlanetDto planetDto) {
        return new ResponseEntity<>(planetService.update(id, planetDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planetService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
