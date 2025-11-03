package org.tonysgt.service;

import io.quarkus.logging.Log;
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
                                 .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
    }

    @Override
    @Transactional
    public CategoryDto addCategory(CategoryDto categoryDto) {
        if (categoryDto.getName() == null || categoryDto.getName().isBlank()) {
            Log.error("Category name must not be null or blank");
            throw new IllegalArgumentException("Category name must not be null or blank");
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.persist(category);
        return new CategoryDto(category.id, category.getName());
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findByIdOptional(id)
                                              .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
        category.setName(categoryDto.getName());
        return new CategoryDto(category.id, category.getName());
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        boolean deleted = categoryRepository.deleteById(id);
        if (!deleted) {
            throw new EntityNotFoundException("Category not found: " + id);
        }
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(category -> new CategoryDto(category.id, category.getName()))
                                 .toList();
    }
}
