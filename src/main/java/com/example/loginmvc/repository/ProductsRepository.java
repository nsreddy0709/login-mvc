package com.example.loginmvc.repository;


import com.example.loginmvc.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Integer> {
    Products findProductsByPtitle(String title);
}
