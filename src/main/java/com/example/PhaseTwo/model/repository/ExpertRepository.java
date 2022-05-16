package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert,Long> {
 List<Expert> findExpertBySubServicesContaining(SubService service);
 @Query(" select t from Expert t where  (t.users.firstname=:firstname or :firstname is null ) and (t.users.lastname=:lastname or :lastname is null )" +
         " and (t.users.email=:email or :email is null )" +
         "and (t.subServices=:subService or :subService is null ) ")
 List<Expert> optionalFiltering(@Param("firstname") String firstname,
                                @Param("lastname") String lastname,
                                @Param("email") String email
         , @Param("subService") SubService subService);
}
