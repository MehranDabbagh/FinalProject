package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.service.impl.ServiceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/Service")
public class ServiceController {
    private ServiceServiceImpl service;

    public ServiceController(ServiceServiceImpl service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<Services> findById(@PathVariable("id") Long id) {
        Services services = service.findById(id);
        if (services != null) {
            return ResponseEntity.ok(services);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Services> save(@Valid @RequestBody Services services) {

            Services services1 = service.save(services);
            if (services1 != null) {
                return ResponseEntity.ok(services1);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Services> update(@Valid @RequestBody Services services) {
            service.update(services);
            return ResponseEntity.ok(services);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Services> delete(@PathVariable("id") Long id) {
        Services services = service.findById(id);
        if (services != null) {
            service.delete(services);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Services>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
