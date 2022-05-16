package com.example.PhaseTwo.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Component
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
}
