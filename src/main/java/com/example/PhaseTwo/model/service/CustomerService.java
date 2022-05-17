package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.entity.SubService;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    void update(Customer customer);
    void changingPassword(Long Id,String password);
    Customer findById(Long id);
    List<Customer> findAll();
    void delete(Customer customer);
    List<Customer> findByOptional(String firstname, String lastname, String email);

}
