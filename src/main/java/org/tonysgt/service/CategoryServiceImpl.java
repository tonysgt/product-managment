package org.tonysgt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.tonysgt.dto.CategoryDto;
import org.tonysgt.entities.Category;

import java.util.List;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDto getCategory(Long id) {
        Category category = Category.findById(id);
        return new CategoryDto(category.id, category.getName());
    }

    @Override
    @Transactional
    public CategoryDto addCategory(CategoryDto product) {
        Category category = new Category();
        category.setName(product.getName());
        category.persist();
        return new CategoryDto(category.id, category.getName());
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto product) {
        Category category = Category.findById(id);
        category.setName(product.getName());
        return new CategoryDto(category.id, category.getName());
    }

    @Override
    @Transactional
    public void deleCategory(Long id) {
        Category.deleteById(id);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> list = Category.findAll().list();
        return list.stream().map(category -> new CategoryDto(category.id, category.getName())).toList();
    }
}
