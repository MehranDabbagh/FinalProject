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
    @Override
    public void changingPassword(Long Id, String password) {
        Admin admin = adminRepository.findById(Id).orElse(null);
        if (admin != null && admin.getUsers().passwordChecking(password)) {
            admin.getUsers().setPassword(password);
            adminRepository.save(admin);
        }
    }
}
