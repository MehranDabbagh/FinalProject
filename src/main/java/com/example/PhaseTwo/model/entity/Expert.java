package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
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
public class Expert extends Users {
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    private Long point;
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(
            name = "subService_experts",
            joinColumns = @JoinColumn(name = "subServicei_d"),
            inverseJoinColumns = @JoinColumn(name = "expert_id"))
    private Set<SubService> subServices;

}
