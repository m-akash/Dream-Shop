package com.akash.Dream_Shop.Service.Category;

import com.akash.Dream_Shop.Model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryByID(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategoryById(Long id);
}
