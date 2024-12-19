package com.akash.Dream_Shop.Controller;

import com.akash.Dream_Shop.DTO.ProductDto;
import com.akash.Dream_Shop.Exceptions.AlreadyFoundException;
import com.akash.Dream_Shop.Exceptions.ProductNotFoundException;
import com.akash.Dream_Shop.Model.Product;
import com.akash.Dream_Shop.Request.AddProductRequest;
import com.akash.Dream_Shop.Request.ProductUpdateRequest;
import com.akash.Dream_Shop.Response.ApiResponse;
import com.akash.Dream_Shop.Service.Product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest addProductRequest) {
        try {
            Product product = productService.addProduct(addProductRequest);
            return ResponseEntity.ok(new ApiResponse("Product Added Successfully!", product));
        } catch (AlreadyFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProduct(products);
        return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
    }

    @GetMapping("/product/{id}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            ProductDto productDTO = productService.convertToDTO(product);
            return ResponseEntity.ok(new ApiResponse("Found ", productDTO));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/by/brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            List<ProductDto> convertedProducts = productService.getConvertedProduct(products);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String category) {
        try {
            List<Product> productCate = productService.getProductsByCategory(category);
            List<ProductDto> convertedProducts = productService.getConvertedProduct(productCate);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            List<Product> productBraAndName = productService.getProductsByBrandAndName(brand, name);
            List<ProductDto> convertedProducts = productService.getConvertedProduct(productBraAndName);
            return ResponseEntity.ok(new ApiResponse("Found", productBraAndName));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        try {
            List<Product> productCateAndBrand = productService.getProductsByCategoryAndBrand(category, brand);
            List<ProductDto> convertedProducts = productService.getConvertedProduct(productCateAndBrand);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));

        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/name")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name) {
        try {
            List<Product> productName = productService.getProductByName(name);
            List<ProductDto> convertedProducts = productService.getConvertedProduct(productName);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/category-and-name")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndName(@RequestParam String category, @RequestParam String name) {
        try {
            List<Product> productCateAndName = productService.getProductsByCategoryAndName(category, name);
            List<ProductDto> convertedProducts = productService.getConvertedProduct(productCateAndName);
            return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/product/{id}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest product) {
        try {
            Product upProduct = productService.updateProduct(product, id);
            ProductDto productDto = productService.convertToDTO(upProduct);
            return ResponseEntity.ok(new ApiResponse("Update Product Successfully!", upProduct));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{id}/delete")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Product delete successfully!", null));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
