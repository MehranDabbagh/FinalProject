package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Admin;
import com.example.PhaseTwo.model.repository.AdminRepository;
import com.example.PhaseTwo.model.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

}
