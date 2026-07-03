package com.POS.system.payload.dto;

import com.POS.system.Model.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDto {

    private Long id;  // id is needed to convert product to productDto

    private String name;

    private String description;

    private String sku;

    private Double mrp;
    private Double sellingPrice;

    private String brand;
    private String image;

    private CategoryDto categoryDto;

    private Long categoryId;

    private Long storeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
