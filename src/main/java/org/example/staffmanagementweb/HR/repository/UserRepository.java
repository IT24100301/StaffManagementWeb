package org.example.staffmanagementweb.HR.repository;

import org.example.staffmanagementweb.HR.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
