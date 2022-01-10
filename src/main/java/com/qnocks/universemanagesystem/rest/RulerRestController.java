package com.qnocks.universemanagesystem.rest;

import com.qnocks.universemanagesystem.dto.RulerDto;
import com.qnocks.universemanagesystem.service.RulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rulers")
@RequiredArgsConstructor
public class RulerRestController {

    private final RulerService rulerService;

    @GetMapping
    public ResponseEntity<List<RulerDto>> list() {
        return new ResponseEntity<>(rulerService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RulerDto> save(@RequestBody RulerDto rulerDto) {
        return new ResponseEntity<>(rulerService.save(rulerDto), HttpStatus.CREATED);
    }

    @GetMapping("free")
    public ResponseEntity<List<RulerDto>> getFreeRulers() {
        return new ResponseEntity<>(rulerService.getFreeRulers(), HttpStatus.OK);
    }

    @GetMapping("new")
    public ResponseEntity<List<RulerDto>> getYoungestRulers() {
        return new ResponseEntity<>(rulerService.getTop10Youngest(), HttpStatus.OK);
    }
}
