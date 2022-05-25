package com.example.PhaseTwo.model.entity.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PasswordChangingDto {
    @NotEmpty
    private Long id;
    @NotEmpty
    @Size(min = 8)
    private String password;

    public PasswordChangingDto(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
