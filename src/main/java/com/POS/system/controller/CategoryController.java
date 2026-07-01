package com.POS.system.controller;

import com.POS.system.payload.dto.CategoryDto;
import com.POS.system.payload.response.ApiResponse;
import com.POS.system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws Exception {
        CategoryDto newCategory=categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(newCategory);
    }


    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByStoreId(@PathVariable Long storeId) throws Exception {
        List<CategoryDto> categories=categoryService.getCategoriesByStore(storeId);
        return ResponseEntity.ok(categories);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                      @RequestBody CategoryDto categoryDto
    ) throws Exception {
        CategoryDto category=categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws Exception {
        categoryService.deleteCategory(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("category deleted Successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
