package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert,Long> {
}
