package com.example.loginmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories(basePackages = "com.example.loginmvc.repository")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Configuration(proxyBeanMethods = false)


public class LoginMvcApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LoginMvcApplication.class, args);
    }

}
