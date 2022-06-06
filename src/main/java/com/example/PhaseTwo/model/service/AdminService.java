package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Admin;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.entity.Services;
import com.example.PhaseTwo.model.entity.Users;
import com.example.PhaseTwo.model.entity.dto.AdminDto;
import com.example.PhaseTwo.model.entity.dto.UserFiltering;

import java.util.List;

public interface AdminService {
    AdminDto save(Admin admin);

    AdminDto update(AdminDto admin);

    AdminDto findById(Long id);

    List<AdminDto> findAll();

    void delete(AdminDto admin);

    void changingPassword(Long Id, String password);

    List<Users> filteringOptional(UserFiltering userFiltering);

    Boolean verifyingExpert(Long id);

}
