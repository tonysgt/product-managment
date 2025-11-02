package org.tonysgt.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.tonysgt.dto.CreateProductDto;
import org.tonysgt.dto.ProductDto;
import org.tonysgt.entities.Category;
import org.tonysgt.entities.Product;

import java.util.List;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDto> getProducts() {
        List<Product> list = Product.findAll().list();
        return list.stream().map(ProductServiceImpl::getProductDto).toList();
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = Product.findById(id);
        return getProductDto(product);
    }

    private static ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.id);
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }

    @Override
    @Transactional
    public ProductDto createProduct(CreateProductDto createProductDto) {
        Product product = new Product();
        product.setCode(createProductDto.getCode());
        product.setName(createProductDto.getName());
        product.setPrice(createProductDto.getPrice());
        product.setQuantity(createProductDto.getQuantity());
        Category.find("name=?1", createProductDto.getCategory())
                .singleResultOptional()
                .ifPresent(category -> product.setCategory((Category) category));
        product.persist();
        Product createdProduct = Product.find("code=?1", createProductDto.getCode()).singleResult();
        return getProductDto(createdProduct);
    }

    @Override
    public ProductDto update(Long id,CreateProductDto updateProduct) {
        Product product = Product.findById(id);
        product.setCode(updateProduct.getCode());
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setQuantity(updateProduct.getQuantity());
        Category.find("name=?1", updateProduct.getCategory()).singleResultOptional().ifPresent(category ->product.setCategory((Category) category));
        return getProductDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        boolean deleted = Product.deleteById(id);
        if (deleted) {
            Log.infof("Entity with id {} deleted!", id);
        }
    }
}
