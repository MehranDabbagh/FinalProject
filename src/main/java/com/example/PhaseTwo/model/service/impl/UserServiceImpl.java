package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Users;
import com.example.PhaseTwo.model.entity.dto.UserFiltering;
import com.example.PhaseTwo.model.registration.token.ConfirmationToken;
import com.example.PhaseTwo.model.registration.token.ConfirmationTokenService;
import com.example.PhaseTwo.model.repository.CustomerRepository;
import com.example.PhaseTwo.model.repository.ExpertRepository;
import com.example.PhaseTwo.model.repository.UsersRepository;
import com.example.PhaseTwo.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private UsersRepository usersRepository;
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
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


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return usersRepository.findByUsername(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Users appUser) {
        boolean userExists = usersRepository
                .findByUsername(appUser.getUsername())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        usersRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return usersRepository.enableAppUser(email);
    }

}
