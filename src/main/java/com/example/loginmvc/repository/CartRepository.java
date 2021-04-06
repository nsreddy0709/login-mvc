package com.example.loginmvc.repository;


import com.example.loginmvc.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public interface CartRepository extends JpaRepository<Cart,Integer> {



    Cart findCartByUidAndPid(Integer id1,Integer id2);

    List<Cart> findCartByUid(Integer id);

    List<Cart> findCartByPid(Integer id);

    void deleteCartByUidAndPid(Integer id1,Integer id2);

    void deleteCartByUid(Integer id);




}
