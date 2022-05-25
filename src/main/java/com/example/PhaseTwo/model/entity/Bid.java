package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotEmpty
    @ManyToOne
    private Expert expert;
    @NotEmpty
    @ManyToOne
    private Orders orders;
    @NotEmpty
    private LocalDateTime bidDate;
    @NotEmpty
    private Long offer;
    @NotEmpty
    private Long hoursNeeded;
    @NotEmpty
    private LocalDateTime timeToStart;
    private Boolean accepted;

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", expert=" + expert +
                ", orders=" + orders +
                ", bidDate=" + bidDate +
                ", offer=" + offer +
                ", hoursNeeded=" + hoursNeeded +
                ", timeToStart=" + timeToStart +
                ", accepted=" + accepted +
                '}';
    }
}
