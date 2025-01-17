package com.example.loginmvc.repository;


import com.example.loginmvc.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findOrdersByUid(Integer id);
}
