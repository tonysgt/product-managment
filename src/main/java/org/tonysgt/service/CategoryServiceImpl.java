package org.tonysgt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.tonysgt.dto.CategoryDto;
import org.tonysgt.entities.Category;
import org.tonysgt.repository.CategoryRepository;

import java.util.List;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    @Override
    public CategoryDto getCategory(Long id) {
        return categoryRepository.findByIdOptional(id)
                .map(category -> new CategoryDto(category.id, category.getName()))
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
    }

    @Override
    @Transactional
    public CategoryDto addCategory(CategoryDto product) {
        Category category = new Category();
        category.setName(product.getName());
        categoryRepository.persist(category);
        return new CategoryDto(category.id, category.getName());
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto product) {
        Category category = categoryRepository.findById(id);
        category.setName(product.getName());
        return new CategoryDto(category.id, category.getName());
    }

    @Override
    @Transactional
    public void deleCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryDto(category.id, category.getName()))
                .toList();
    }
}
