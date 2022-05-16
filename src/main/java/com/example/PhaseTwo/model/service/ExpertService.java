package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.SubService;

public interface ExpertService {
    Expert save(Expert expert);
    Expert update(Expert expert);
    Expert findById(Long id);
    Expert linkingExpertToSubService(Expert expert, SubService subService);
}
