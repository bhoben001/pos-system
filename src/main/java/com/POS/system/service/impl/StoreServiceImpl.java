package com.POS.system.service.impl;

import com.POS.system.Domain.StoreStatus;
import com.POS.system.Mapper.StoreMapper;
import com.POS.system.Model.Store;
import com.POS.system.Model.StoreContact;
import com.POS.system.Model.User;
import com.POS.system.exception.UserException;
import com.POS.system.payload.dto.StoreDto;
import com.POS.system.repository.StoreRepository;
import com.POS.system.service.StoreService;
import com.POS.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService  userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto,user);
        user.setStore(store);
        Store savedStore = storeRepository.save(store);
        return StoreMapper.toDto(savedStore);
    }

    @Override
    public StoreDto getStoreById(Long id) throws UserException {
        Store store = storeRepository.findById(id).orElseThrow(()-> new UserException("store not found"));
        return StoreMapper.toDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> stores=storeRepository.findAll();
        List<StoreDto> storeDtos = new ArrayList<>();
        for (Store store : stores) {
            storeDtos.add(StoreMapper.toDto(store));
        }
        return storeDtos;
    }

    @Override
    public Store getStoreByAdmin() {
        User user=userService.getCurrentUser();
        Store store=storeRepository.findByStoreAdminId(user.getId());
        return store;
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws Exception {

        User currentUser=userService.getCurrentUser();

        Store existing=storeRepository.findByStoreAdminId(currentUser.getId());

        if(existing==null)throw new Exception("Store not found");

        if(storeDto.getBrand()!=null)existing.setBrand(storeDto.getBrand());
        if(storeDto.getDescription()!=null)existing.setDescription(storeDto.getDescription());

        if(storeDto.getStoreType()!=null)existing.setStoreType(storeDto.getStoreType());

        if (storeDto.getContact() != null) {
            StoreContact contact = existing.getContact();
            if (contact == null) contact = new StoreContact();
            if (storeDto.getContact().getAddress() != null) contact.setAddress(storeDto.getContact().getAddress());
            if (storeDto.getContact().getEmail() != null)contact.setEmail(storeDto.getContact().getEmail());
            if (storeDto.getContact().getPhone() != null) contact.setPhone(storeDto.getContact().getPhone());
            existing.setContact(contact);
        }
        Store updatedStore=storeRepository.save(existing);

        return StoreMapper.toDto(updatedStore);
    }

    @Override
    public void deleteStore(Long id){
        Store store=getStoreByAdmin();
        storeRepository.delete(store);
    }


    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User CurrentUser=userService.getCurrentUser();
        if(CurrentUser==null) throw new UserException("you don't have permission to access this store");
        return StoreMapper.toDto(CurrentUser.getStore());
    }

//    updating store status
    @Override
    public StoreDto moderateStore(Long id, StoreStatus storeStatus) throws UserException {
        Store store=storeRepository.findById(id).orElseThrow(()-> new UserException("store not found"));
        store.setStatus(storeStatus);
        Store savedStore = storeRepository.save(store);
        return StoreMapper.toDto(savedStore);
    }
}
