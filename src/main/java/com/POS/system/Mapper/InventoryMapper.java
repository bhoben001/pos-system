package com.POS.system.Mapper;

import com.POS.system.Model.Branch;
import com.POS.system.Model.Inventory;
import com.POS.system.Model.Product;
import com.POS.system.Model.Store;
import com.POS.system.payload.dto.BranchDto;
import com.POS.system.payload.dto.InventoryDto;

import java.time.LocalDateTime;

public class InventoryMapper {


    public static InventoryDto toDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDto(inventory.getProduct()))
                .branch(BranchMapper.toDto(inventory.getBranch()))
                .quantity(inventory.getQuantity())
                .lastUpdate(inventory.getLastUpdate())
                .build();
    }


    public static Inventory toEntity(InventoryDto dto,Branch branch, Product product) {
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(dto.getQuantity())
                .build();

    }
}
