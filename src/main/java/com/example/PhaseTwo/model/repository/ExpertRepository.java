package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert,Long> {
}
