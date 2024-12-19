package com.akash.Dream_Shop.Request;

import com.akash.Dream_Shop.Model.Category;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private String description;
    private int inventory;
    private Category category;
}
