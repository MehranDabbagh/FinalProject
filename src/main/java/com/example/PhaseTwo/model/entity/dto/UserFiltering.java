package com.example.PhaseTwo.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UserFiltering {
    private LocalDateTime singUpDateAfter;
    private LocalDateTime singUpDateBefore;
    private Long numberOfActivities;



}
