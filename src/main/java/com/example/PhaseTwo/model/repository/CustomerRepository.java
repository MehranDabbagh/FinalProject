package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
