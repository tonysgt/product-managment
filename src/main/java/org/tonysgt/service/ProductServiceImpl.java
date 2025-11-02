package org.tonysgt.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.tonysgt.dto.AddProductDto;
import org.tonysgt.dto.ProductDto;
import org.tonysgt.entities.Category;
import org.tonysgt.entities.OutboxEvent;
import org.tonysgt.entities.Product;
import org.tonysgt.repository.CategoryRepository;
import org.tonysgt.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    ObjectMapper mapper;

    @Inject
    ProductRepository productRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductServiceImpl::getProductDto)
                .toList();
    }

    @Override
    public ProductDto getProduct(Long id) {
        return productRepository.findByIdOptional(id)
                .map(ProductServiceImpl::getProductDto)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
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
    public ProductDto addProduct(AddProductDto createProductDto) {
        Product product = new Product();
        product.setCode(createProductDto.getCode());
        product.setName(createProductDto.getName());
        product.setPrice(createProductDto.getPrice());
        product.setQuantity(createProductDto.getQuantity());
        categoryRepository.find("name=?1", createProductDto.getCategory())
                .firstResultOptional()
                .ifPresent(product::setCategory);
        productRepository.persist(product);
        Product createdProduct = productRepository.find("code=?1", createProductDto.getCode()).singleResult();
        // Save an OutboxEvent in the same transaction
        writeEventInOutbox(createdProduct, "ProductAdded");
        return getProductDto(createdProduct);
    }

    private void writeEventInOutbox(Product product, String eventType) {
        try {
            OutboxEvent event = new OutboxEvent();
            event.setAggregateId(product.id.toString());
            event.setEventType(eventType);
            event.setPayload(mapper.convertValue(product, new TypeReference<Map<String, Object>>() {
            })); // Serialize the order to JSON or similar format
            event.setProcessed(false);
            event.setCreatedAt(LocalDateTime.now());
            event.persist();
        } catch (Exception e) {
            Log.error("Failed to write OutboxEvent for product id " + product.id, e);
            throw e;
        }
    }

    @Override
    @Transactional
    public ProductDto update(Long id, AddProductDto updateProduct) {
        Product product = productRepository.findById(id);
        product.setCode(updateProduct.getCode());
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setQuantity(updateProduct.getQuantity());
        categoryRepository.find("name=?1", updateProduct.getCategory()).firstResultOptional().ifPresent(product::setCategory);
        writeEventInOutbox(product, "ProductUpdated");
        return getProductDto(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        boolean deleted = productRepository.deleteById(id);
        if (deleted) {
            Log.infof("Entity with id %d deleted!", id);
        }
    }
}
