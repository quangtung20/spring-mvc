package com.quangtung.springbootblogwebapp.service;

import com.quangtung.springbootblogwebapp.dto.RegistrationDto;
import com.quangtung.springbootblogwebapp.entity.Role;
import com.quangtung.springbootblogwebapp.entity.User;
import com.quangtung.springbootblogwebapp.repository.RoleRepository;
import com.quangtung.springbootblogwebapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User newUser = modelmapper.map(registrationDto, User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        List<Role> listRole =  newUser.getRoles();
        Role role = roleRepository.findByName("USER").get();
        listRole.add(role);
        newUser.setRoles(listRole);
        userRepository.save(newUser);
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }
}
