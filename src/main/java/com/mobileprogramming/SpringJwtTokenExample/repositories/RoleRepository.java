package com.mobileprogramming.SpringJwtTokenExample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobileprogramming.SpringJwtTokenExample.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
