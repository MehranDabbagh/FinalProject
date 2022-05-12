package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.entity.Status;
import com.example.PhaseTwo.model.repository.OrdersRepository;
import com.example.PhaseTwo.model.service.OrderService;
import org.hibernate.type.LocalDateTimeType;
import org.hibernate.type.LocalDateType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    private OrdersRepository ordersRepository;
    private CustomerServiceImpl customerService;

    public OrderServiceImpl(OrdersRepository ordersRepository, CustomerServiceImpl customerService) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
    }

    @Override
    public Orders buildingOrder(Orders orders, Customer customer) {
        if (orders == null) {
            return null;
        } else if (orders.getRequiredDate().isAfter(LocalDateTime.now()) || orders.getId() != null || orders.getAddress() == null || orders.getPrice() == null) {
            return null;
        } else {
            orders.setSingUpDate(LocalDateTime.now());
            orders.setStatus(Status.Bid);
            orders.setCustomer(customer);
            Orders orders1 = ordersRepository.save(orders);
            customerService.update(customer);
            return orders1;
        }
    }
}
