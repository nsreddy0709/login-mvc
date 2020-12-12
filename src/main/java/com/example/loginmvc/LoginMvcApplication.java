package com.example.loginmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.loginmvc.repository")
@SpringBootApplication
public class LoginMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginMvcApplication.class, args);
    }

}
