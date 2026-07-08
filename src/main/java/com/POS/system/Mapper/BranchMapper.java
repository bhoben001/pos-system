package com.POS.system.Mapper;

import com.POS.system.Model.Branch;
import com.POS.system.Model.Category;
import com.POS.system.Model.Store;
import com.POS.system.payload.dto.BranchDto;
import com.POS.system.payload.dto.CategoryDto;

import java.time.LocalDateTime;

public class BranchMapper {


    public static BranchDto toDto(Branch branch) {
        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .workingDays(branch.getWorkingDays())
                .openTime(branch.getOpenTime())
                .closeTime(branch.getCloseTime())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .store(branch.getStore() !=null? StoreMapper.toDto(branch.getStore()):null)
                .storeId(branch.getStore()!=null?branch.getStore().getId():null)
                .build();
    }


    public static Branch toEntity(BranchDto dto, Store store) {
        return Branch.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .workingDays(dto.getWorkingDays())
                .openTime(dto.getOpenTime())
                .closeTime(dto.getCloseTime())
                .store(store)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
