package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private Orders orders;
    private LocalDateTime bidDate;
    private Long offer;
    private Long hoursNeeded;
    private LocalDateTime timeToStart;
    private Boolean accepted;

}
