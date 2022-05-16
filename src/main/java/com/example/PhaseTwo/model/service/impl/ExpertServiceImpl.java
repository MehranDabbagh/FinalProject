package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Role;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.repository.ExpertRepository;
import com.example.PhaseTwo.model.repository.SubServiceRepository;
import com.example.PhaseTwo.model.service.ExpertService;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl implements ExpertService {
    private ExpertRepository expertRepository;
    private SubServiceRepository subServiceRepository;

    public ExpertServiceImpl(ExpertRepository expertRepository, SubServiceRepository subServiceRepository) {
        this.expertRepository = expertRepository;
        this.subServiceRepository = subServiceRepository;
    }

    @Override
    public Expert save(Expert expert) {
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

    @Override
    public Expert linkingExpertToSubService(Expert expert, SubService subService) {
        Expert expert1 = expertRepository.findById(expert.getId()).orElse(null);
        SubService subService1 = subServiceRepository.findById(subService.getId()).orElse(null);
        if (expert1 != null && subService1 != null) {
            expert1.getSubServices().add(subService1);
            expertRepository.save(expert1);
            return expert1;
        }
        return null;
    }

}