package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Expert;

public interface ExpertService {
    Expert save(Expert expert);
    Expert update(Expert expert);
    Expert findById(Long id);
}
