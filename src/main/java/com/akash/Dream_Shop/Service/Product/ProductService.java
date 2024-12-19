package com.akash.Dream_Shop.Service.Product;

import com.akash.Dream_Shop.DTO.ImageDto;
import com.akash.Dream_Shop.DTO.ProductDto;
import com.akash.Dream_Shop.Exceptions.AlreadyFoundException;
import com.akash.Dream_Shop.Exceptions.ProductNotFoundException;
import com.akash.Dream_Shop.Model.Category;
import com.akash.Dream_Shop.Model.Image;
import com.akash.Dream_Shop.Model.Product;
import com.akash.Dream_Shop.Repository.CategoryRepository;
import com.akash.Dream_Shop.Repository.ImageRepository;
import com.akash.Dream_Shop.Repository.ProductRepository;
import com.akash.Dream_Shop.Request.AddProductRequest;
import com.akash.Dream_Shop.Request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    /*Create product start*/
    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
        if (ExistsProductAlready(addProductRequest.getName(), addProductRequest.getBrand())) {
            throw new AlreadyFoundException("This Product is Already Exist, you may update this instead!");
        } else {
            Category category = Optional.ofNullable(categoryRepository.findByName(addProductRequest.getCategory().getName()))

                    /* Start Create new Category if category doesn't exist*/
                    .orElseGet(() -> {
                        Category newCategory = new Category(addProductRequest.getCategory().getName());
                        return categoryRepository.save(newCategory);
                    });
            /* End Create new Category if category doesn't exist*/

            addProductRequest.setCategory(category);
            return productRepository.save(CreateProduct(addProductRequest, category));
        }
    }

    public boolean ExistsProductAlready(String name, String brand) {
        return productRepository.existsByNameAndBrand(name, brand);
    }

    public Product CreateProduct(AddProductRequest addProductRequest, Category category) {
        return new Product(
                addProductRequest.getName(),
                addProductRequest.getBrand(),
                addProductRequest.getPrice(),
                addProductRequest.getInventory(),
                addProductRequest.getDescription(),
                category
        );
    }
    /*Create product End*/

    /*Start Updating product*/
    @Override
    public Product updateProduct(ProductUpdateRequest product, Long id) {
        //If product found, then update
        return productRepository.findById(id)
                .map(existingProduct -> UpdateExistingProduct(existingProduct, product))
                .map(productRepository::save)

                //If product not found throw this exception
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    public Product UpdateExistingProduct(Product existingProduct, ProductUpdateRequest UpdateRequest) {
        existingProduct.setName(UpdateRequest.getName());
        existingProduct.setBrand(UpdateRequest.getBrand());
        existingProduct.setPrice(UpdateRequest.getPrice());
        existingProduct.setDescription(UpdateRequest.getDescription());
        existingProduct.setInventory(UpdateRequest.getInventory());
        Category category = categoryRepository.findByName(UpdateRequest.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }
    /*End Updating product*/

    /*Start Deleting product*/
    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
                () -> {
                    throw new ProductNotFoundException("Product Not Found!");
                });
    }
    /*End Deleting product*/

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByCategoryAndName(String category, String name) {
        return productRepository.findByCategoryNameAndName(category, name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertedProduct(List<Product> products) {
        return products.stream().map(this::convertToDTO).toList();
    }

    @Override
    public ProductDto convertToDTO(Product product) {
        ProductDto productDTO = modelMapper.map(product, ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDTOs = images.stream().map(image -> modelMapper.map(image, ImageDto.class)).toList();
        productDTO.setImages(imageDTOs);
        return productDTO;
    }
}
