package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.repository.ServiceRepository;
import com.example.PhaseTwo.model.repository.SubServiceRepository;
import com.example.PhaseTwo.model.service.SubServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubServiceServiceImpl implements SubServiceService {
    private SubServiceRepository subServiceRepository;
    private ServiceRepository serviceRepository;

    public SubServiceServiceImpl(SubServiceRepository subServiceRepository, ServiceRepository serviceRepository) {
        this.subServiceRepository = subServiceRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public SubService save(SubService subService) {
        Services services=serviceRepository.findById(subService.getServicesCategory().getId()).orElse(null);
            if(services!=null){
                return subServiceRepository.save(subService);
            }else return null;
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
