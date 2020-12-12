package com.example.loginmvc.resource;


import com.example.loginmvc.model.Users;
import com.example.loginmvc.repository.UsersRepository;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/users")
public class UserResource {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @PostMapping(value = "/register")
    public List<Users> persist(@RequestBody final Users users){
        usersRepository.save(users);
        return usersRepository.findAll();
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public boolean isValid(@RequestParam Map<String, String> requestparam) {
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
            System.out.println("Exception");
        }
        if(u!=null && p!=null){
            return true;
        }
        return false;
    }
}
