package org.tonysgt.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tonysgt.dto.CreateProductDto;
import org.tonysgt.dto.ProductDto;
import org.tonysgt.service.ProductService;

import java.util.List;

@ApplicationScoped
public class ProductResourceImpl implements ProductResource {

    @Inject
    ProductService productService;

    @Override
    @RolesAllowed({"admin","user"})
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @Override
    @RolesAllowed({"admin","user"})
    public ProductDto getProduct(Long id) {
        return productService.getProduct(id);
    }

    @Override
    @RolesAllowed("admin")
    public ProductDto createProduct(CreateProductDto product) {
        return productService.createProduct(product);
    }

    @Override
    @RolesAllowed("admin")
    public ProductDto updateProduct(Long id, CreateProductDto product) {
        return productService.update(id,product);
    }

    @Override
    @RolesAllowed("admin")
    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }
}
