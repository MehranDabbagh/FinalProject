package com.example.PhaseTwo.controller;

import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.service.impl.SubServiceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SubService> save(@RequestBody SubService subservice) {
        if (checkingInputObject(subservice)) {
            SubService customer1 = subServiceService.save(subservice);
            if (customer1 != null) {
                return ResponseEntity.ok(customer1);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<SubService> update(@RequestBody SubService subservice) {
        if (checkingInputObject(subservice)) {
            subServiceService.update(subservice);
            return ResponseEntity.ok(subservice);
        }
        return ResponseEntity.badRequest().build();
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


    private Boolean checkingInputObject(SubService subservice) {
        if (subservice.getServicesCategory() == null ||
                subservice.getDescription() == null ||
                subservice.getName() == null ||
                subservice.getPrice() == null) {
            return false;
        }
        return true;
    }
}
