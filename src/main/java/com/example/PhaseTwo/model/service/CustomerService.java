package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.entity.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto save(CustomerDto customer);
    void update(CustomerDto customer);
    void changingPassword(Long Id,String password);
    CustomerDto findById(Long id);
    List<CustomerDto> findAll();
    void delete(CustomerDto customer);
    List<CustomerDto> findByOptional(String firstname, String lastname, String email);

}
