package com.example.PhaseTwo.controller;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.entity.dto.AdminDto;
import com.example.PhaseTwo.model.entity.dto.CustomerDto;
import com.example.PhaseTwo.model.entity.dto.PasswordChangingDto;
import com.example.PhaseTwo.model.entity.dto.PayingWithCredit;
import com.example.PhaseTwo.model.service.impl.CustomerServiceImpl;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerServiceImpl customerService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        String password = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(password);
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

    @PostMapping("/passwordChanging")
    public ResponseEntity<CustomerDto> changingPassword(@Valid @RequestBody PasswordChangingDto passwordChangingDto) {
        customerService.changingPassword(passwordChangingDto.getId(), passwordChangingDto.getPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/History")
    public ResponseEntity<List<Orders>> findByExpertId(@Valid Long id) {
        List<Orders> orders = customerService.findOrders(id);
        if (orders != null) {
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/orderFinishing/{bidId}")
    public ResponseEntity<Object> orderFinishing(@Valid @RequestBody Orders orders,@PathVariable("bidId") Long bidId){
        customerService.finishingOrder(orders.getCustomer().getId(),orders.getId(),bidId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/payingWithCredit")
    public ResponseEntity<Object> payingWithCredit(@Valid PayingWithCredit payingWithCredit){
        customerService.payingWithCredit(payingWithCredit);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/payingOnline")
    public ResponseEntity<Object> payingOnline(){
        return ResponseEntity.ok().build();
    }

}
