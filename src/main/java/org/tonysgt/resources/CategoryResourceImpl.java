package org.tonysgt.resources;

import org.tonysgt.dto.AddProductDto;
import org.tonysgt.dto.ProductDto;

import java.util.List;

public class CategoryResourceImpl implements CategoryResource {
    @Override
    public List<ProductDto> getAllCategories() {
        return List.of();
    }

    @Override
    public ProductDto getCategory(Long id) {
        return null;
    }

    @Override
    public ProductDto addCategory(AddProductDto product) {
        return null;
    }


    @Override
    public ProductDto updateCategory(Long id, AddProductDto product) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
