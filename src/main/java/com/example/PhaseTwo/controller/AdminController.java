package com.example.PhaseTwo.controller;

import com.example.PhaseTwo.model.entity.Admin;
import com.example.PhaseTwo.model.entity.dto.AdminDto;
import com.example.PhaseTwo.model.entity.dto.CustomerDto;
import com.example.PhaseTwo.model.entity.dto.PasswordChangingDto;
import com.example.PhaseTwo.model.service.impl.AdminServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<AdminDto> findById(@PathVariable("id") Long id) {
        AdminDto customer = adminService.findById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else
            return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping()
    public ResponseEntity<AdminDto> save(@Valid @RequestBody Admin admin) {

        AdminDto customer1 = adminService.save(admin);
        if (customer1 != null) {
            return ResponseEntity.ok(customer1);
        }
        return ResponseEntity.badRequest().build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<AdminDto> update(@Valid @RequestBody AdminDto customer) {
        adminService.update(customer);
        return ResponseEntity.ok(customer);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AdminDto> delete(@PathVariable("id") Long id) {
        AdminDto customer = adminService.findById(id);
        if (customer != null) {
            adminService.delete(customer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<List<AdminDto>> findAll() {
        return ResponseEntity.ok(adminService.findAll());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/passwordChanging")
    public ResponseEntity<AdminDto> changingPassword(@Valid @RequestBody PasswordChangingDto passwordChangingDto) {
        adminService.changingPassword(passwordChangingDto.getId(), passwordChangingDto.getPassword());
        return ResponseEntity.ok().build();
    }
}
