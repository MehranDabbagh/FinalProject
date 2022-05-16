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
    public void changingPassword(Long Id,String password) {
        Users users =usersRepository.findById(Id).orElse(null);
        if(users !=null && users.passwordChecking(password)){
            users.setPassword(password);
            usersRepository.save(users);
        }
    }
}
