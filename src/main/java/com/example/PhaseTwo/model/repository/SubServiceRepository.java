package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService,Long> {
}
