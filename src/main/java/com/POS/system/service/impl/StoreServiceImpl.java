package com.POS.system.service.impl;

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
        return null;
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws Exception {
        User currentUser=userService.getCurrentUser();

        Store existing=storeRepository.findByStoreAdminId(currentUser.getId());

        if(existing==null)throw new Exception("Store not found");

        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());

        if(storeDto.getStoreType()!=null)existing.setStoreType(storeDto.getStoreType());
        if(storeDto.getContact()!=null) {
            StoreContact contact=StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .email(storeDto.getContact().getEmail())
                    .phone(storeDto.getContact().getPhone())
                    .build();
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
}
