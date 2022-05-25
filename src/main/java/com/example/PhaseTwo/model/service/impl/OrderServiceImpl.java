package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Bid;
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
import java.util.InputMismatchException;
import java.util.List;

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

        if (orders.getRequiredDate().isAfter(LocalDateTime.now())) {
            throw new InputMismatchException("wrong date!");
        } else {
            orders.setSingUpDate(LocalDateTime.now());
            orders.setStatus(Status.Bid);
            orders.setCustomer(customer);
            Orders orders1 = ordersRepository.save(orders);
            customerService.update(customerService.convertingToDto(customer));
            return orders1;
        }
    }

    @Override
    public Orders findById(Long id) {
        Orders orders = ordersRepository.findById(id).orElse(null);
        if (orders != null) {
            return orders;
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders update(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public void delete(Orders orders) {
        ordersRepository.deleteById(orders.getId());
    }


}
