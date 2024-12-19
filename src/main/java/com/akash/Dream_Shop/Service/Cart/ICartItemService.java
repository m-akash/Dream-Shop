package com.akash.Dream_Shop.Service.Cart;

import com.akash.Dream_Shop.Model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId, Long productId, int quantity);
    void removeItemToCart(Long cartId, Long productId);
    void updateItemToCart(Long cartId, Long productId, int quantity);
    CartItem getCartItem(Long cartId, Long productId);
}
