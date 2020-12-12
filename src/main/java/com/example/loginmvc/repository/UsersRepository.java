package com.example.loginmvc.repository;

import com.example.loginmvc.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByEmail(String email);

    Users findByPassword(String password);
}
