package com.example.loginmvc.repository;

import com.example.loginmvc.model.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByEmail(String email);

    Users findByPassword(String password);
    Users findUsersByEmail(String email);

    Users findUsersByEmailAndPassword(String email,String password);
}
