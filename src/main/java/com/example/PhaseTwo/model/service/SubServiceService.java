package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.entity.SubService;

import java.util.List;

public interface SubServiceService {
    SubService save(SubService subService);
    SubService update(SubService subService);
    SubService findById(Long id);
    List<SubService> findAll();
    void delete(SubService subService);
}
