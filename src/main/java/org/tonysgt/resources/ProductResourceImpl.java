package org.tonysgt.resources;

import io.quarkus.logging.Log;
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
        Log.info("Adding product " + product.getName());
        return productService.addProduct(product);
    }

    @Override
    public ProductDto updateProduct(Long id, AddProductDto product) {
        Log.info("Updating product " + product.getName());
        return productService.update(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        Log.info("Deleting product " + id);
        productService.deleteProduct(id);
    }
}
