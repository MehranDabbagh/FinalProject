package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Admin;
import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.dto.AdminDto;
import com.example.PhaseTwo.model.repository.AdminRepository;
import com.example.PhaseTwo.model.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminDto save(Admin admin) {
        Admin admin1=adminRepository.save(admin);
        if(admin1!=null) {
            return convertingToDto(admin1);
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    @Override
    public AdminDto update(AdminDto admin) {
        Admin admin1=adminRepository.save(convertingToAdmin(admin));
        if(admin1!=null) {
            return convertingToDto(admin1);
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    @Override
    public AdminDto findById(Long id) {
        Admin admin=adminRepository.findById(id).orElse(null);
        if(admin!=null) {
            return convertingToDto(admin);
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    @Override
    public List<AdminDto> findAll() {
        List<Admin> admins = adminRepository.findAll().stream().collect(Collectors.toList());
        return admins.stream().map(admin -> convertingToDto(admin)).collect(Collectors.toList());
    }

    @Override
    public void delete(AdminDto admin) {
        adminRepository.deleteById(admin.getId());
    }

    @Override
    public void changingPassword(Long Id, String password) {
        Admin admin = adminRepository.findById(Id).orElse(null);
        if (admin != null && admin.passwordChecking(password)) {
            admin.setPassword(password);
            adminRepository.save(admin);
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    public AdminDto convertingToDto(Admin admin) {
        AdminDto adminDto = new AdminDto(admin.getId(), admin.getFirstname(), admin.getLastname(), admin.getEmail(), admin.getVerified(), admin.getSingUpDate(), admin.getCredit());
        return adminDto;
    }

    public  Admin convertingToAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setFirstname(adminDto.getFirstName());
        admin.setLastname(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        admin.setSingUpDate(adminDto.getSingUpDate());
        admin.setCredit(adminDto.getCredit());
        admin.setVerified(adminDto.getVerified());
        admin.setRole(adminDto.getRole());
        return admin;
    }
}
