package com.POS.system.payload.dto;

import com.POS.system.Model.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private String sku;

    private double mrp;
    private double sellingPrice;
    private String brand;
    private String image;

    private CategoryDto categoryDto;

    private Long categoryId;

    private Long storeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
