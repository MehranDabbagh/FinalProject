package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
