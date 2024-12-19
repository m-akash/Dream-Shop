package com.akash.Dream_Shop.DTO;

import com.akash.Dream_Shop.Model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDto product;
}