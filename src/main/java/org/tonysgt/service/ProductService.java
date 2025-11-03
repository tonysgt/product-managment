package org.tonysgt.service;

import org.tonysgt.dto.AddProductDto;
import org.tonysgt.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto getProduct(Long id);

    ProductDto addProduct(AddProductDto product);

    ProductDto update(Long id, AddProductDto product);

    void deleteProduct(Long id);

    List<ProductDto> getProducts();

}
