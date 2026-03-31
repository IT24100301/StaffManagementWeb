package org.example.staffmanagementweb.HR.repository;

import org.example.staffmanagementweb.HR.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}

