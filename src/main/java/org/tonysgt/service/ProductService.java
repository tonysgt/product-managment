package org.tonysgt.service;

import org.tonysgt.dto.CreateProductDto;
import org.tonysgt.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto getProduct(Long id);

    ProductDto createProduct(CreateProductDto product);

    ProductDto update(Long id,CreateProductDto product);

    void deleteProduct(Long id);

    List<ProductDto> getProducts();

}
