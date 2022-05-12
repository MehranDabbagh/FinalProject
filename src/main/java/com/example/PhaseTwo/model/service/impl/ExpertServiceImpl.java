package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Role;
import com.example.PhaseTwo.model.repository.ExpertRepository;
import com.example.PhaseTwo.model.service.ExpertService;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl implements ExpertService {
    private ExpertRepository expertRepository;

    public ExpertServiceImpl(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    @Override
    public Expert save(Expert expert) {
        expert.setRole(Role.EXPERT);
        return expertRepository.save(expert);
    }

    @Override
    public Expert update(Expert expert) {

        return expertRepository.save(expert);
    }

    @Override
    public Expert findById(Long id) {
        return expertRepository.findById(id).orElse(null);
    }

}
