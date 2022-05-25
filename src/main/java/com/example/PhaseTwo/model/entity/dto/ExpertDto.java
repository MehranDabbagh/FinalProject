package com.example.PhaseTwo.model.entity.dto;

import com.example.PhaseTwo.model.entity.Role;
import com.example.PhaseTwo.model.entity.SubService;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

public class ExpertDto {
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
    private byte[] image;
    private Long point;
    private Set<SubService> subServices;

    public ExpertDto(Long id, String firstName, String lastName, String email, Boolean verified, LocalDateTime singUpDate, Long credit, byte[] image, Long point, Set<SubService> subServices) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.verified = verified;
        this.singUpDate = singUpDate;
        this.credit = credit;
        this.image = image;
        this.point = point;
        this.subServices = subServices;
        this.role=Role.EXPERT;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Set<SubService> getSubServices() {
        return subServices;
    }

    public void setSubServices(Set<SubService> subServices) {
        this.subServices = subServices;
    }
}
