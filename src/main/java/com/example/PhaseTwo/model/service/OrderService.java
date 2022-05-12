package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Orders;

public interface OrderService {
    Orders buildingOrder(Orders orders, Customer customer);
}
