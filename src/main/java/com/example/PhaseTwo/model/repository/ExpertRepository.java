package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.entity.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert,Long> {
 List<Expert> findExpertBySubServicesContaining(SubService service);

 @Query("select t from Expert t join Bid m on t.id=m.expert.id and m.accepted=true where t.singUpDate>=:singUpAfter and t.singUpDate<=:singUpBefore and count(m)>:numberOfBid ")
 List<Expert> optionalFinding(@Param("singUpAfter") LocalDateTime singUpAfter,
                                @Param("singUpBefore")LocalDateTime singUpBefore,
                                @Param("numberOfBid")Long numberOfBid);

}
