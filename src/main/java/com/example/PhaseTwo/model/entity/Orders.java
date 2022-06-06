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
    @NotEmpty
    @ManyToOne
    private Customer customer;
    @NotEmpty
    @ManyToOne
    private SubService subService;
    @NotEmpty
    private Long price;
    @NotEmpty
    private String description;
    @NotEmpty
    private LocalDateTime singUpDate;
    @NotEmpty
    private LocalDateTime requiredDate;
    @NotEmpty
    private String address;

    private Status status;
    @NotEmpty
    @ManyToOne
    private Services services;

}
