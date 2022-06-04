package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.*;
import com.example.PhaseTwo.model.entity.dto.CustomerDto;
import com.example.PhaseTwo.model.entity.dto.PayingWithCredit;

import java.util.List;

public interface CustomerService {
    CustomerDto save(Customer customer);
    void update(CustomerDto customer);
    void changingPassword(Long Id,String password);
    CustomerDto findById(Long id);
    List<CustomerDto> findAll();
    void delete(CustomerDto customer);
    List<CustomerDto> findByOptional(String firstname, String lastname, String email);
    List<Orders> findOrders(Long CustomerId);
    void finishingOrder(Long customerId,Long orderId);
    void payingWithCredit(PayingWithCredit payingWithCredit);

}
