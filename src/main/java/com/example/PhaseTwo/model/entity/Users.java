package com.example.PhaseTwo.model.entity;

import com.example.PhaseTwo.model.entity.Role;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @NotEmpty
    @Size(min = 8)
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
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    @Column(unique = true)
    private String email;
    private Boolean verified;
    @NotEmpty
    private LocalDateTime singUpDate;
    private Long credit;


}
