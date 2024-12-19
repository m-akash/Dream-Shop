package com.akash.Dream_Shop.Service.Product;

import com.akash.Dream_Shop.DTO.ProductDto;
import com.akash.Dream_Shop.Request.AddProductRequest;
import com.akash.Dream_Shop.Request.ProductUpdateRequest;
import com.akash.Dream_Shop.Model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest addProductRequest);
    Product getProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long id);
    void deleteProductById(Long id);


    List<Product> getAllProducts();
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductsByCategoryAndName(String category, String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);


    List<ProductDto> getConvertedProduct(List<Product> products);
    ProductDto convertToDTO(Product product);
}
