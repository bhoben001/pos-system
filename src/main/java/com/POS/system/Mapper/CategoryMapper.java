package com.POS.system.Mapper;

import com.POS.system.Model.Category;
import com.POS.system.payload.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .storeId(category.getStore()!=null?category.getStore().getId() :null)
                .build();
    }
}
