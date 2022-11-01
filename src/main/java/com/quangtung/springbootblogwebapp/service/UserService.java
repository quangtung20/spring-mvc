package com.quangtung.springbootblogwebapp.service;

import com.quangtung.springbootblogwebapp.dto.RegistrationDto;
import com.quangtung.springbootblogwebapp.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findUserByEmail(String email);
}
