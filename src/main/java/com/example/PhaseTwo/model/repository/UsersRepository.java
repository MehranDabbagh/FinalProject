package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
