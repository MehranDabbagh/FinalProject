package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Role;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String password;
    private Role role;
    public Boolean passwordChecking(String password) {
        if (password.length() >= 8) {
            char[] chars = password.toCharArray();
            for (char char1 : chars
            ) {
                if (Character.isDigit(char1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private Boolean verified;
    private LocalDateTime singUpDate;
    private Long credit;


}
