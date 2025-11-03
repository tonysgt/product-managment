package org.tonysgt.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tonysgt.dto.AddProductDto;
import org.tonysgt.dto.CategoryDto;
import org.tonysgt.dto.ProductDto;
import org.tonysgt.service.CategoryService;

import java.util.List;

@ApplicationScoped
public class CategoryResourceImpl implements CategoryResource {

    @Inject
    CategoryService categoryService;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryService.getCategories();
    }

    @Override
    public CategoryDto getCategory(Long id) {
        return categoryService.getCategory(id);
    }

    @Override
    public CategoryDto addCategory(CategoryDto category) {
        return categoryService.addCategory(category);
    }


    @Override
    public CategoryDto updateCategory(Long id, CategoryDto category) {
        return categoryService.updateCategory(id, category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryService.deleteCategory(id);
    }
}
