package com.example.PhaseTwo.controller;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.entity.dto.CustomerDto;
import com.example.PhaseTwo.model.service.impl.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") Long id) {
        CustomerDto customer = customerService.findById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody Customer customer) {

            CustomerDto customer1 = customerService.save(customer);
            if (customer1 != null) {
                return ResponseEntity.ok(customer1);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto customer) {
            customerService.update(customer);
            return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> delete(@PathVariable("id") Long id) {
        CustomerDto customer = customerService.findById(id);
        if (customer != null) {
            customerService.delete(customer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("{firstname},{lastname},{email}")
    public ResponseEntity<List<CustomerDto>> filtering(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,
                                                       @PathVariable("email") String email) {
        List<CustomerDto> customers = customerService.findByOptional(firstname, lastname, email);
        if (customers.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

}
