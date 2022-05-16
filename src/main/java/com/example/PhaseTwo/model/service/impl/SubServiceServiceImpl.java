package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.repository.SubServiceRepository;
import com.example.PhaseTwo.model.service.SubServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubServiceServiceImpl implements SubServiceService {
    private SubServiceRepository subServiceRepository;

    public SubServiceServiceImpl(SubServiceRepository subServiceRepository) {
        this.subServiceRepository = subServiceRepository;
    }

    @Override
    public SubService save(SubService subService) {
        return subServiceRepository.save(subService);
    }

    @Override
    public SubService update(SubService subService) {
        return subServiceRepository.save(subService);
    }

    @Override
    public SubService findById(Long id) {
        return subServiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<SubService> findAll() {
        return subServiceRepository.findAll();
    }

    @Override
    public void delete(SubService subService) {
        subServiceRepository.deleteById(subService.getId());
    }
}
