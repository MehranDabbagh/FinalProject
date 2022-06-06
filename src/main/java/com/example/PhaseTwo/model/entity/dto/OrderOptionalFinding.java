package com.example.PhaseTwo.model.entity.dto;

import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.entity.Status;
import com.example.PhaseTwo.model.entity.SubService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class OrderOptionalFinding {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private SubService subService;
    private Services services;

    public OrderOptionalFinding(LocalDateTime startTime, LocalDateTime endTime, Status status, SubService subService, Services services) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.subService = subService;
        this.services = services;
    }

}
