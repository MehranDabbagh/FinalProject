package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Users users;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    private Long point;
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(
            name = "subService_experts",
            joinColumns = @JoinColumn(name = "subServicei_d"),
            inverseJoinColumns = @JoinColumn(name = "expert_id"))

    private Set<SubService> subServices;

}
