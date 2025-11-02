package org.tonysgt.service;

import org.tonysgt.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategory(Long id);

    CategoryDto addCategory(CategoryDto product);

    CategoryDto updateCategory(Long id, CategoryDto product);

    void deleCategory(Long id);

    List<CategoryDto> getCategories();
}
