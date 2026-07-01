package com.POS.system.Mapper;

import com.POS.system.Model.Store;
import com.POS.system.Model.User;
import com.POS.system.payload.dto.StoreDto;

import java.time.LocalDateTime;

public class StoreMapper {
    public static StoreDto toDto(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setBrand(store.getBrand());
        storeDto.setDescription(store.getDescription());
        storeDto.setStoreAdmin(UserMapper.toDto(store.getStoreAdmin()));
        storeDto.setStoreType(store.getStoreType());
        storeDto.setContact(store.getContact());
        storeDto.setCreatedAt(store.getCreatedAt());
        storeDto.setUpdatedAt(store.getUpdatedAt());
        storeDto.setStatus(store.getStatus());

        return storeDto;
    }

    public static Store toEntity(StoreDto storeDto, User storeAdmin) {
        Store store = new Store();
        store.setBrand(storeDto.getBrand());
        store.setDescription(storeDto.getDescription());
        store.setStoreAdmin( storeAdmin);
        store.setStoreType(storeDto.getStoreType());
        store.setContact(storeDto.getContact());
        store.setCreatedAt(LocalDateTime.now());
        store.setUpdatedAt(LocalDateTime.now());
        store.setStatus(storeDto.getStatus());
        return store;
    }
}
