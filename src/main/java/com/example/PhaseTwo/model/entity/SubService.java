package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SubService extends Service {
    private Long price;
    private String description;
    @ManyToOne
    private Service serviceCategory;

}
