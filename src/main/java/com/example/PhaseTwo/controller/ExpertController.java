package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Expert;
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
    public ResponseEntity<Expert> findById(@PathVariable("id") Long id) {
        Expert expert = expertService.findById(id);
        if (expert != null) {
            return ResponseEntity.ok(expert);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Expert> save(@RequestBody Expert expert) {
        if (checkingInputObject(expert)) {
            Expert expert1 = expertService.save(expert);
            if (expert1 != null) {
                return ResponseEntity.ok(expert1);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Expert> update(@RequestBody Expert customer) {
        if (checkingInputObject(customer)) {
            expertService.update(customer);
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expert> delete(@PathVariable("id") Long id) {
        Expert expert = expertService.findById(id);
        if (expert != null) {
            expertService.delete(expert);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("{firstname},{lastname},{email},{subServiceId}")
    public ResponseEntity<List<Expert>> filtering(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,
                                                  @PathVariable("email") String email, @PathVariable("subServiceId")Long subServiceId) {
        List<Expert> customers = expertService.findByOptional(firstname, lastname, email,subServiceId);
        if (customers.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    private Boolean checkingInputObject(Expert expert) {
        if (expert == null || expert.getUsers() == null) {
            return false;
        }
        if (expert.getUsers().getEmail() == null || expert.getUsers().getFirstname() == null
                || expert.getUsers().getLastname() == null || !expert.getUsers().passwordChecking(expert.getUsers().getPassword())) {
            return false;
        }
        return true;

    }
}
