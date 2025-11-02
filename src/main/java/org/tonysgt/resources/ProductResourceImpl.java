package org.tonysgt.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tonysgt.dto.AddProductDto;
import org.tonysgt.dto.ProductDto;
import org.tonysgt.service.ProductService;

import java.util.List;

@ApplicationScoped
public class ProductResourceImpl implements ProductResource {

    @Inject
    ProductService productService;

    @Override
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @Override
    public ProductDto getProduct(Long id) {
        return productService.getProduct(id);
    }

    @Override
    public ProductDto addProduct(AddProductDto product) {
        return productService.addProduct(product);
    }

    @Override
    public ProductDto updateProduct(Long id, AddProductDto product) {
        return productService.update(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }
}
