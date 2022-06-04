package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("select t from Orders t where t.customer.id=:id ")
    List<Orders> findByCustomerId(@Param("id") Long customerId);

}
