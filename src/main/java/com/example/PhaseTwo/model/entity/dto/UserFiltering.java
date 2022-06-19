package com.example.PhaseTwo.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
@Getter
@Setter
public class UserFiltering {
    @NotEmpty
    private LocalDateTime singUpDateAfter;
    @NotEmpty
    private LocalDateTime singUpDateBefore;
    @NotEmpty
    private Long numberOfActivities;



}
