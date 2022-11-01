package com.quangtung.springbootblogwebapp.repository;

import com.quangtung.springbootblogwebapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
