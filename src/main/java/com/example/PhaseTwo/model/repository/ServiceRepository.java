package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
