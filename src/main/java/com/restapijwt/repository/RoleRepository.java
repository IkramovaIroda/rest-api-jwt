package com.restapijwt.repository;


import com.restapijwt.entity.Role;
import com.restapijwt.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleEnum name);
}
