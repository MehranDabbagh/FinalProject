package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Users;
import com.example.PhaseTwo.model.repository.UsersRepository;
import com.example.PhaseTwo.model.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);

    }

    @Override
    public Boolean verification(Long id) {
        Users users = usersRepository.findById(id).orElse(null);
        if (users != null) {
            return users.getVerified();
        }
        throw new NullPointerException("wrong id!");
    }
}
