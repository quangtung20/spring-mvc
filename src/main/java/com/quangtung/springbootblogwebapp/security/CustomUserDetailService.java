package com.quangtung.springbootblogwebapp.security;

import com.quangtung.springbootblogwebapp.entity.User;
import com.quangtung.springbootblogwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();
        if(user!=null){
            org.springframework.security.core.userdetails.User authencateUser =
                    new org.springframework.security.core.userdetails.User(
                            user.getEmail(),
                            user.getPassword(),
                            user.getRoles().stream()
                                    .map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
                    );
            System.out.println("authencateUser = " + authencateUser);
            return authencateUser;
        }else {
            throw new UsernameNotFoundException("invalid username or password");
        }
    }
}
