package com.akash.Dream_Shop.Service.Cart;

import com.akash.Dream_Shop.Model.Cart;
import com.akash.Dream_Shop.Model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}