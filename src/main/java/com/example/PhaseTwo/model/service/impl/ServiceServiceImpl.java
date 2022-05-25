package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.repository.ServiceRepository;
import com.example.PhaseTwo.model.service.ServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    private ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Services save(Services services) {
        return serviceRepository.save(services);
    }

    @Override
    public Services update(Services services) {
        return serviceRepository.save(services);
    }

    @Override
    public Services findById(Long id) {
        Services services = serviceRepository.findById(id).orElse(null);
        if (services != null) {
            return services;
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public List<Services> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public void delete(Services services) {
        serviceRepository.deleteById(services.getId());
    }
}
