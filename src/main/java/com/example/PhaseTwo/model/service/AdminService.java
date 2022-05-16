package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Admin;
import com.example.PhaseTwo.model.entity.Services;

import java.util.List;

public interface AdminService {
    Admin save(Admin admin);
    Admin update(Admin admin);
    Admin findById(Long id);
    List<Admin> findAll();
    void delete(Admin admin);
    void changingPassword(Long Id,String password);
}
