package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.*;
import com.example.PhaseTwo.model.repository.OrdersRepository;
import com.example.PhaseTwo.model.repository.ServiceRepository;
import com.example.PhaseTwo.model.repository.SubServiceRepository;
import com.example.PhaseTwo.model.service.OrderService;
import org.hibernate.type.LocalDateTimeType;
import org.hibernate.type.LocalDateType;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrdersRepository ordersRepository;
    private CustomerServiceImpl customerService;
    private SubServiceRepository subServiceRepository;
    private ServiceRepository serviceRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository, CustomerServiceImpl customerService, SubServiceRepository subServiceRepository, ServiceRepository serviceRepository) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
        this.subServiceRepository = subServiceRepository;
        this.serviceRepository = serviceRepository;
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

    @Override
    public List<Orders> filteringOrdersByExample(LocalDateTime startTime, LocalDateTime endTime, Status status, SubService subService, Services services) {

        List<Orders> orders = ordersRepository.findAll(createOrderExample(status,
                subServiceRepository.findById(subService.getId()).orElse(null)
                , serviceRepository.findById(services.getId()).orElse(null)));
        return orders.stream().filter(x -> x.getSingUpDate().isAfter(startTime) && x.getSingUpDate().isBefore(endTime)).collect(Collectors.toList());
    }

    @Override
    public Boolean finishingOrder(Long orderId) {
        Orders orders=ordersRepository.findById(orderId).orElse(null);
        if(orders==null || orders.getStatus()!=Status.Started){
            return false;
        }
        orders.setStatus(Status.Done);
        ordersRepository.save(orders);
        return true;
    }

    private Example<Orders> createOrderExample(Status status, SubService subService, Services services) {
        Orders orders = new Orders();
        orders.setStatus(status);
        orders.setSubService(subService);
        orders.setServices(services);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase("status", "subService", "services")
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Orders> usersExample = Example.of(orders, matcher);
        return usersExample;
    }

}
