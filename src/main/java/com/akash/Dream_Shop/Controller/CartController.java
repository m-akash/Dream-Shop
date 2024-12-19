package com.akash.Dream_Shop.Controller;

import com.akash.Dream_Shop.Exceptions.ResourceNotFoundException;
import com.akash.Dream_Shop.Model.Cart;
import com.akash.Dream_Shop.Response.ApiResponse;
import com.akash.Dream_Shop.Service.Cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {
    private final ICartService iCartService;

    @GetMapping("/{id}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long id) {
        try {
            Cart cart = iCartService.getCart(id);
            return ResponseEntity.ok(new ApiResponse("Found", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long id) {
        try {
            iCartService.clearCart(id);
            return ResponseEntity.ok(new ApiResponse("Clear successful", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not Found", null));
        }
    }

    @GetMapping("/{id}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalPrice(@PathVariable Long id) {
        try {
            BigDecimal totalPrice = iCartService.getTotalPrice(id);
            return ResponseEntity.ok(new ApiResponse("Total Price ", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
