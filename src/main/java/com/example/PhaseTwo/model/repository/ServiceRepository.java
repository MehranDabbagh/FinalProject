package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services,Long> {
}
