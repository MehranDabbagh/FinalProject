package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {
    @Query("select t from Bid t where t.orders.id=:id order by t.offer asc ")
    List<Bid> sortByPrice(@Param("id") Long id);
    @Query("select t from Bid t where t.orders.id=:id order by t.expert.point desc ")
    List<Bid> sortByExpertPoint(@Param("id") Long id);
}
