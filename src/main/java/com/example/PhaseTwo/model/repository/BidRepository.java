package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid,Long> {
}
