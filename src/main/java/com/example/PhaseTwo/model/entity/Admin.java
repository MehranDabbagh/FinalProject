package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Users {

}
