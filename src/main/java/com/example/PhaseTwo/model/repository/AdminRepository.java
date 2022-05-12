package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
