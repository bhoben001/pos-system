package com.POS.system.payload.dto;

import com.POS.system.Model.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;  // hame id -> category to category-dto  map karne ke liye chahiye otherwise we don't need it

    private String categoryName;

    private Long storeId;
}
