package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select t from Customer t join Orders m on t.id=m.customer.id where t.singUpDate>=:singUpAfter and t.singUpDate<=:singUpBefore and count(m)>:numberOfOrders")
    List<Customer> optionalFinding(@Param("singUpAfter")LocalDateTime singUpAfter,
                                   @Param("singUpBefore")LocalDateTime singUpBefore,
                                   @Param("numberOfOrders")Long numberOfOrders);
}
