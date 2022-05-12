package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)

    private Customer customer;
    private Long price;
    private String description;
    private LocalDateTime singUpDate;
    private LocalDateTime requiredDate;
    private String address;
    private Status status;

}
