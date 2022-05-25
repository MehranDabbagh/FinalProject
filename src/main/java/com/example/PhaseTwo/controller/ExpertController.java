package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.entity.dto.ExpertDto;
import com.example.PhaseTwo.model.service.impl.ExpertServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expert")
public class ExpertController {
    private ExpertServiceImpl expertService;

    public ExpertController(ExpertServiceImpl expertService) {
        this.expertService = expertService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpertDto> findById(@PathVariable("id") Long id) {
        ExpertDto expert = expertService.findById(id);
        if (expert != null) {
            return ResponseEntity.ok(expert);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<ExpertDto> save(@RequestBody ExpertDto expert) {
        if (checkingInputObject(expert)) {
            ExpertDto expert1 = expertService.save(expert);
            if (expert1 != null) {
                return ResponseEntity.ok(expert1);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<ExpertDto> update(@RequestBody ExpertDto customer) {
        if (checkingInputObject(customer)) {
            expertService.update(customer);
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExpertDto> delete(@PathVariable("id") Long id) {
        ExpertDto expert = expertService.findById(id);
        if (expert != null) {
            expertService.delete(expert);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("{firstname},{lastname},{email},{subServiceId}")
    public ResponseEntity<List<ExpertDto>> filtering(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,
                                                     @PathVariable("email") String email, @PathVariable("subServiceId") Long subServiceId) {
        List<ExpertDto> customers = expertService.findByOptional(firstname, lastname, email, subServiceId);
        if (customers.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @PostMapping("link/{expertId},{subServiceId}")
    public ResponseEntity<ExpertDto> linkingExpertToSubService(@PathVariable("expertId") Long expertId, @PathVariable("subServiceId") Long subServiceId) {
        SubService subService = new SubService();
        subService.setId(subServiceId);
        ExpertDto expert = expertService.findById(expertId);
        if (expert != null) {
            ExpertDto expert1 = expertService.linkingExpertToSubService(expert, subService);
            return ResponseEntity.ok(expert1);
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ExpertDto>> findAll() {
        return ResponseEntity.ok(expertService.findAll());
    }

    private Boolean checkingInputObject(ExpertDto expert) {
        if (expert == null) {
            return false;
        }
        return true;

    }
}
