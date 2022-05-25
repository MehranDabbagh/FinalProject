package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Admin;
import com.example.PhaseTwo.model.repository.AdminRepository;
import com.example.PhaseTwo.model.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin save(Admin admin) {
        Admin admin1=adminRepository.save(admin);
        if(admin1!=null) {
            return admin1;
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    @Override
    public Admin update(Admin admin) {
        Admin admin1=adminRepository.save(admin);
        if(admin1!=null) {
            return admin1;
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    @Override
    public Admin findById(Long id) {
        Admin admin=adminRepository.findById(id).orElse(null);
        if(admin!=null) {
            return admin;
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public void delete(Admin admin) {
        adminRepository.deleteById(admin.getId());
    }

    @Override
    public void changingPassword(Long Id, String password) {
        Admin admin = adminRepository.findById(Id).orElse(null);
        if (admin != null && admin.getUsers().passwordChecking(password)) {
            admin.getUsers().setPassword(password);
            adminRepository.save(admin);
        }
        throw new NullPointerException("there is no admin with this id!");
    }
}
