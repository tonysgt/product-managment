package org.tonysgt.dto;

import org.tonysgt.entities.Product;

import java.math.BigDecimal;

public class ProductDto extends CreateProductDto {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
