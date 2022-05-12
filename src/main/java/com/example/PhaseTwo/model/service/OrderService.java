package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.repository.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrdersRepository ordersRepository;
    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

}
