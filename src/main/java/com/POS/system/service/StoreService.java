package com.POS.system.service;

import com.POS.system.Model.Store;
import com.POS.system.Model.User;
import com.POS.system.exception.UserException;
import com.POS.system.payload.dto.StoreDto;
import java.util.List;

public interface StoreService {
    StoreDto createStore(StoreDto storeDto, User user);

    StoreDto getStoreById(Long id) throws UserException;

    List<StoreDto> getAllStores();

    Store getStoreByAdmin();

    StoreDto updateStore(Long id, StoreDto storeDto) throws Exception;

    void deleteStore(Long id);

    StoreDto getStoreByEmployee() throws UserException;
}
