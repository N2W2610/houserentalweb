package com.trotot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trotot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}