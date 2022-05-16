package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Users;

public interface UserService {
    Users save(Users user);
    void changingPassword(Long Id,String password);
}
