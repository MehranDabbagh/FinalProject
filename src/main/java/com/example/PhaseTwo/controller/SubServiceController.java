package com.example.PhaseTwo.controller;

import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.service.impl.SubServiceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subService")
public class SubServiceController {
    private SubServiceServiceImpl subServiceService;

    public SubServiceController(SubServiceServiceImpl subServiceService) {
        this.subServiceService = subServiceService;
    }

    @GetMapping("{id}")
    public ResponseEntity<SubService> findById(@PathVariable("id") Long id) {
        SubService subservice = subServiceService.findById(id);
        if (subservice != null) {
            return ResponseEntity.ok(subservice);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<SubService> save(@Valid @RequestBody SubService subservice) {

            SubService subService = subServiceService.save(subservice);
            if (subService != null) {
                return ResponseEntity.ok(subService);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<SubService> update(@Valid @RequestBody SubService subservice) {
            subServiceService.update(subservice);
            return ResponseEntity.ok(subservice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubService> delete(@PathVariable("id") Long id) {
        SubService subservice = subServiceService.findById(id);
        if (subservice != null) {
            subServiceService.delete(subservice);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SubService>> findAll() {
        return ResponseEntity.ok(subServiceService.findAll());
    }
}
