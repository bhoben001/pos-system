package com.POS.system.Mapper;

import com.POS.system.Model.Category;
import com.POS.system.Model.Product;
import com.POS.system.Model.Store;
import com.POS.system.payload.dto.ProductDto;

import java.time.LocalDateTime;

public class ProductMapper {
    public static ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setSku(product.getSku());
        productDto.setMrp(product.getMrp());
        productDto.setSellingPrice(product.getSellingPrice());
        productDto.setBrand(product.getBrand());
        productDto.setImage(product.getImage());
        productDto.setCategoryDto(CategoryMapper.toDto(product.getCategory()));
        productDto.setStoreId(product.getStore()!=null?product.getStore().getId():null);
        productDto.setCreatedAt(LocalDateTime.now());
        productDto.setUpdatedAt(LocalDateTime.now());
        return productDto;
    }

    public static Product toEntity(ProductDto productDto, Store store, Category category) {
        Product product = new Product();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setBrand(productDto.getBrand());
        product.setCategory(category);


        return product;
    }
}
