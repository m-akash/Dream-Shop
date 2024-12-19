package com.akash.Dream_Shop.Repository;


import com.akash.Dream_Shop.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}