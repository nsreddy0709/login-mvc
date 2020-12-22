package com.example.loginmvc.resource;


import com.example.loginmvc.config.JwtResponse;
import com.example.loginmvc.config.JwtTokenUtil;

import com.example.loginmvc.model.Users;
import com.example.loginmvc.repository.UsersRepository;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping(value = "/rest/users")
public class UserResource {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping(value = "/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @PostMapping(value = "/register")
    public List<Users> persist(@Valid @RequestBody final Users users){
        usersRepository.save(users);
        return usersRepository.findAll();
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<?> isValid(@RequestParam Map<String, String> requestparam) {
        Users u = null;
        Users p = null;
        try{
            String email = requestparam.get("email");
            String password = requestparam.get("password");
            u = usersRepository.findByEmail(email);
            p = usersRepository.findByPassword(password);
            System.out.println(u);
            System.out.println(email);
        }
        catch (Exception e){
            return ResponseEntity.ok("Invaid credentials");
        }
        if(u!=null && p!=null){

            final String token = jwtTokenUtil.generateToken(u.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.ok("Invaid credentials");
    }
    @RequestMapping({ "/hello" })
    public String hello() {
        return "Hello JWT";
    }
}
