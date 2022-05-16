package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Services;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    void update(Customer customer);
    void changingPassword(Long Id,String password);
    Customer findById(Long id);
    List<Customer> findAll();
    void delete(Customer customer);
}
