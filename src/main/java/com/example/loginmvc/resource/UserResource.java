package com.example.loginmvc.resource;


import com.example.loginmvc.config.JwtResponse;
import com.example.loginmvc.config.JwtTokenUtil;

import com.example.loginmvc.config.LoginError;
import com.example.loginmvc.model.*;
import com.example.loginmvc.repository.*;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping(value = "/rest/users")
public class UserResource {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    Orders1Repository orders1Repository;

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
            u = usersRepository.findUsersByEmailAndPassword(email,password);
            //p = usersRepository.findByPassword(password);
            System.out.println(u);
            System.out.println(email);
        }
        catch (Exception e){
            return ResponseEntity.ok("Invaid credentials");
        }
        if(u!=null){

            final String token = jwtTokenUtil.generateToken(u.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.ok(new JwtResponse("Invalid credential"));
    }
    @RequestMapping({ "/hello" })
    public String hello() {
        return "Hello JWT";
    }

    @PostMapping(value = "/addtocart")
    public ResponseEntity<?> persist(@RequestParam String title,String token){
        String name = jwtTokenUtil.extractUsername(title);
        System.out.println(name);
        System.out.println(token);
        Users users = null;
        Products product = productsRepository.findProductsByPtitle(title);
        System.out.println(product);
        System.out.println(name);
        users = usersRepository.findUsersByEmail(name);
        System.out.println(users);

        Cart cart1 = cartRepository.findCartByUidAndPid(users.getUser_id(),product.getProduct_id());

        if(cart1!=null){
            return ResponseEntity.ok("Already added to cart");
        }
        else {


            Cart cart = new Cart();
            cart.setPid(product.getProduct_id());
            cart.setUid(users.getUser_id());
            cart.setIp_add("imiim");
            cart.setTitle(product.getPtitle());
            cart.setImage(product.getProduct_image());
            cart.setQty(1);
            cart.setPrice(product.getProduct_price());
            cart.setAmount(product.getProduct_price());

            cartRepository.save(cart);


            return ResponseEntity.ok("Succesfully added to cart");
        }
    }

    @GetMapping(value = "/getcartdetails")
    public List<Cart> getCart(@RequestBody String token)
    {
        String name = jwtTokenUtil.extractUsername(token);
        Users users = usersRepository.findUsersByEmail(name);
        return cartRepository.findCartByUid(users.getUser_id());
    }

    @PutMapping(value = "/updateqyt")
    public List<Cart> update(@RequestParam Integer qty, String email, String name)
    {
        name = jwtTokenUtil.extractUsername(name);
        Users users = usersRepository.findUsersByEmail(email);
        System.out.println(users);
        Products product = productsRepository.findProductsByPtitle(name);
        System.out.println(product);
        Cart cart = cartRepository.findCartByUidAndPid(users.getUser_id(),product.getProduct_id());
        System.out.println(cart);
        cart.setQty(qty);
        cart.setAmount(qty * cart.getAmount());
        cartRepository.save(cart);
        return cartRepository.findCartByUid(users.getUser_id());
    }
    @GetMapping(value = "/getamount")
    public Integer toalAmount(@RequestParam String email)
    {
        String name = jwtTokenUtil.extractUsername(email);
        Users users = usersRepository.findUsersByEmail(name);
        List<Orders> orders = ordersRepository.findOrdersByUid(users.getUser_id());
        int sum = 0;
        for (Orders o:
                orders) {
            sum = sum + o.getPrice();
        }

        return sum;
    }

    @DeleteMapping(value = "/deletecart")
    public List<Cart> delete(@RequestParam String name, String email){
        Users users = usersRepository.findUsersByEmail(email);
        Products product = productsRepository.findProductsByPtitle(name);
        cartRepository.deleteCartByUidAndPid(users.getUser_id(),product.getProduct_id());
        return cartRepository.findCartByUid(users.getUser_id());
    }

    @DeleteMapping
    public void deletecarts(@RequestParam String email){
        Users users = usersRepository.findUsersByEmail(email);
        cartRepository.deleteCartByUid(users.getUser_id());
    }

    @PostMapping(value = "/checkout")
    public List<Orders> checkout(@RequestParam String email ){
        Users users = usersRepository.findUsersByEmail(email);
        List<Cart> cart = cartRepository.findCartByUid(users.getUser_id());
        for (Cart c:
                cart) {
            Orders orders = new Orders();
            orders.setUid(c.getUid());
            orders.setPid(c.getPid());
            orders.setPname(c.getTitle());
            orders.setPrice(c.getAmount());
            orders.setQty(c.getQty());
            ordersRepository.save(orders);
            Orders1 orders1 = new Orders1();
            orders1.setUid(c.getUid());
            orders1.setPid(c.getPid());
            orders1.setPname(c.getTitle());
            orders1.setPrice(c.getAmount());
            orders1.setQty(c.getQty());
            orders1Repository.save(orders1);
        }
        return ordersRepository.findOrdersByUid(users.getUser_id());
    }


}
