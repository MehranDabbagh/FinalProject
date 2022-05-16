package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Services;

import java.util.List;

public interface ServiceService {
    Services save(Services services);
    Services update(Services services);
    Services findById(Long id);
    List<Services> findAll();
    void delete(Services services);
}
