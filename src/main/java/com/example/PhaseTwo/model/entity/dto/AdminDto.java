package com.example.PhaseTwo.model.entity.dto;

import com.example.PhaseTwo.model.entity.Role;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class AdminDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private Role role;
    @NotEmpty
    private String email;
    private Boolean verified;
    @NotEmpty
    private LocalDateTime singUpDate;
    private Long credit;

    public AdminDto(Long id, String firstName, String lastName, String email, Boolean verified, LocalDateTime singUpDate, Long credit) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = Role.ADMIN;
        this.email = email;
        this.verified = verified;
        this.singUpDate = singUpDate;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public LocalDateTime getSingUpDate() {
        return singUpDate;
    }

    public void setSingUpDate(LocalDateTime singUpDate) {
        this.singUpDate = singUpDate;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }
}
