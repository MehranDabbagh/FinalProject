package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Customer;

public interface CustomerService {
    Customer save(Customer customer);
    void update(Customer customer);

}
