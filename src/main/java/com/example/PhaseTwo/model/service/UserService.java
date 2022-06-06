package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Users;
import com.example.PhaseTwo.model.entity.dto.UserFiltering;

import java.util.List;

public interface UserService {
    Users save(Users user);

    Boolean verification(Long id);

}
