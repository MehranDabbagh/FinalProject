package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.entity.Services;

import java.util.List;

public interface OrderService {
    Orders buildingOrder(Orders orders, Customer customer);
    Orders findById(Long id);
    Orders save(Orders orders);
    Orders update(Orders orders);
    List<Orders> findAll();
    void delete(Orders orders);

}
