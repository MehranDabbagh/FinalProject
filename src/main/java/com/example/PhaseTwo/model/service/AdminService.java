package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Admin;
import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.entity.dto.AdminDto;

import java.util.List;

public interface AdminService {
    AdminDto save(Admin admin);
    AdminDto update(AdminDto admin);
    AdminDto findById(Long id);
    List<AdminDto> findAll();
    void delete(AdminDto admin);
    void changingPassword(Long Id,String password);
}
