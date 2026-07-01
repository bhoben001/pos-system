package com.POS.system.service;

import com.POS.system.Model.Store;
import com.POS.system.exception.UserException;
import com.POS.system.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception;
    void deleteCategory(Long id) throws Exception;

}
