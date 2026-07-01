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
    private Long id;

    private String categoryName;

//    private Store store;

    private Long storeId;
}
