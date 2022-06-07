package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.*;
import com.example.PhaseTwo.model.entity.dto.AdminDto;
import com.example.PhaseTwo.model.entity.dto.UserFiltering;
import com.example.PhaseTwo.model.repository.AdminRepository;
import com.example.PhaseTwo.model.repository.CustomerRepository;
import com.example.PhaseTwo.model.repository.ExpertRepository;
import com.example.PhaseTwo.model.repository.UsersRepository;
import com.example.PhaseTwo.model.service.AdminService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private CustomerRepository customerRepository;
    private ExpertRepository expertRepository;
    private UsersRepository usersRepository;

    public AdminServiceImpl(AdminRepository adminRepository, CustomerRepository customerRepository, ExpertRepository expertRepository, UsersRepository usersRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.expertRepository = expertRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public AdminDto save(Admin admin) {
        admin.setRole(Role.ROLE_ADMIN);
        admin.setSingUpDate(LocalDateTime.now());
        Admin admin1 = adminRepository.save(admin);
        if (admin1 != null) {
            return convertingToDto(admin1);
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    @Override
    public AdminDto update(AdminDto admin) {
        Admin admin1 = adminRepository.save(convertingToAdmin(admin));
        if (admin1 != null) {
            return convertingToDto(admin1);
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    @Override
    public AdminDto findById(Long id) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
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
        if (admin != null) {
            admin.setPassword(password);
            adminRepository.save(admin);
        }
        throw new NullPointerException("there is no admin with this id!");
    }

    public AdminDto convertingToDto(Admin admin) {
        AdminDto adminDto = new AdminDto(admin.getId(), admin.getFirstname(), admin.getLastname(), admin.getUsername(), admin.getVerified(), admin.getSingUpDate(), admin.getCredit());
        return adminDto;
    }

    @Override
    public List<Users> filteringOptional(UserFiltering userFiltering) {
        List<Users> users = new ArrayList<>();
        customerRepository.optionalFinding(userFiltering.getSingUpDateAfter(),
                        userFiltering.getSingUpDateBefore(),
                        userFiltering.getNumberOfActivities()).stream()
                .forEach(users::add);
        expertRepository.optionalFinding(userFiltering.getSingUpDateAfter(),
                        userFiltering.getSingUpDateBefore(),
                        userFiltering.getNumberOfActivities()).stream()
                .forEach(users::add);
        return users;
    }

    @Override
    public Boolean verifyingExpert(Long id) {
        Expert expert = expertRepository.findById(id).orElse(null);
        if (expert != null) {
            if (expert.isEnabled() && !expert.getVerified()) {
                expert.setVerified(true);
                expertRepository.save(expert);
                return true;
            }
        }
        return false;
    }

    public Admin convertingToAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setFirstname(adminDto.getFirstName());
        admin.setLastname(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setSingUpDate(adminDto.getSingUpDate());
        admin.setCredit(adminDto.getCredit());
        admin.setVerified(adminDto.getVerified());
        admin.setRole(adminDto.getRole());
        return admin;
    }

}
