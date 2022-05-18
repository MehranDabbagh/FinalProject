package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.service.impl.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        if (checkingInputObject(customer)) {
            Customer customer1 = customerService.save(customer);
            if (customer1 != null) {
                return ResponseEntity.ok(customer1);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        if (checkingInputObject(customer)) {
            customerService.update(customer);
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            customerService.delete(customer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("{firstname},{lastname},{email}")
    public ResponseEntity<List<Customer>> filtering(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,
                                                    @PathVariable("email") String email) {
        List<Customer> customers = customerService.findByOptional(firstname, lastname, email);
        if (customers.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    private Boolean checkingInputObject(Customer customer) {
        if (customer == null || customer.getUsers() == null) {
            return false;
        }
        if (customer.getUsers().getEmail() == null || customer.getUsers().getFirstname() == null
                || customer.getUsers().getLastname() == null || !customer.getUsers().passwordChecking(customer.getUsers().getPassword())) {
            return false;
        }
        return true;

    }
}
