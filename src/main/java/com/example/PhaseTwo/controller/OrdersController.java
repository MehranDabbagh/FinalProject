package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.entity.dto.OrderOptionalFinding;
import com.example.PhaseTwo.model.service.impl.OrderServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PutMapping()
    public ResponseEntity<Orders> save(@Valid @RequestBody Orders orders) {

        Orders orders1 = orderService.save(orders);
        if (orders1 != null) {
            return ResponseEntity.ok(orders1);
        }

        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping
    public ResponseEntity<Orders> update(@Valid @RequestBody Orders orders) {

        orderService.update(orders);
        return ResponseEntity.ok(orders);

    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
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

    @PreAuthorize("ROLE_ADMIN")
    @GetMapping("findOptional")
    public ResponseEntity<List<Orders>> optionalFinding(@RequestBody OrderOptionalFinding orderOptionalFinding) {
        return ResponseEntity.ok(orderService.filteringOrdersByExample(orderOptionalFinding.getStartTime()
                , orderOptionalFinding.getEndTime()
                , orderOptionalFinding.getStatus()
                , orderOptionalFinding.getSubService()
                , orderOptionalFinding.getServices()));

    }

    @PreAuthorize("ROLE_CUSTOMER")
    @PostMapping("finishingAnOrder/{orderId}")
    public ResponseEntity<Void> finishing(@Param("orderId") Long orderId) {
        if (orderService.finishingOrder(orderId))
            return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}

