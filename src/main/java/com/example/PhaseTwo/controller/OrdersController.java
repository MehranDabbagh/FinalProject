package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.service.impl.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
    private OrderServiceImpl orderService;

    public OrdersController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Orders> findById(@PathVariable("id") Long id) {
        Orders services = orderService.findById(id);
        if (services != null) {
            return ResponseEntity.ok(services);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Orders> save(@RequestBody Orders orders) {
        if (checkingInputObject(orders)) {
            Orders orders1 = orderService.save(orders);
            if (orders1 != null) {
                return ResponseEntity.ok(orders1);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Orders> update(@RequestBody Orders orders) {
        if (checkingInputObject(orders)) {
            orderService.update(orders);
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> delete(@PathVariable("id") Long id) {
        Orders orders = orderService.findById(id);
        if (orders != null) {
            orderService.delete(orders);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Orders>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }


    private Boolean checkingInputObject(Orders orders) {
        if (orders.getPrice() == null ||
                orders.getAddress() == null ||
                orders.getRequiredDate().isBefore(LocalDateTime.now()) ||
                orders.getCustomer() == null ||
                orders.getSingUpDate() == null ||
                orders.getSubService() == null) {
            return false;
        }
        return true;
    }
}

