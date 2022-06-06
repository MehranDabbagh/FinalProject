package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.*;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Orders buildingOrder(Orders orders, Customer customer);
    Orders findById(Long id);
    Orders save(Orders orders);
    Orders update(Orders orders);
    List<Orders> findAll();
    void delete(Orders orders);
    List<Orders> filteringOrdersByExample(LocalDateTime startTime,LocalDateTime endTime, Status status, SubService subService, Services services);
    Boolean finishingOrder(Long orderId);
}
