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
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private SubService subService;

    private Long price;

    private String description;

    private LocalDateTime singUpDate;

    private LocalDateTime requiredDate;

    private String address;

    private Status status;
    @NotEmpty
    @ManyToOne
    private Services services;

}
