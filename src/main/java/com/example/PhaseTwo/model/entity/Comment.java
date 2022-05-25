package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Bid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotEmpty
    private Long score;
    @NotEmpty
    @ManyToOne
    private Bid bid;
    @NotEmpty
    private String comment;


}
