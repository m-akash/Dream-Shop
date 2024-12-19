package com.akash.Dream_Shop.Repository;

import com.akash.Dream_Shop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrandIgnoreCase(String brand);

    List<Product> findByCategoryName(String category);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String name);

    List<Product> findByCategoryNameAndName(String category, String name);

    Long countByBrandAndName(String brand, String name);

    List<Product> findByBrandAndName(String brand, String name);

    boolean existsByNameAndBrand(String name, String brand);
}
