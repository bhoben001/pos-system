package com.POS.system.controller;

import com.POS.system.Domain.StoreStatus;
import com.POS.system.Mapper.StoreMapper;
import com.POS.system.Model.User;
import com.POS.system.exception.UserException;
import com.POS.system.payload.dto.StoreDto;
import com.POS.system.payload.response.ApiResponse;
import com.POS.system.service.StoreService;
import com.POS.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")

public class StoreController {
    private final StoreService storeService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto,
                                                @RequestHeader("Authorization")String jwt) throws UserException {
            User user=userService.getUserFromJwtToken(jwt);
            return  ResponseEntity.ok(storeService.createStore(storeDto,user));
    }

//    hasRole
    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) throws UserException {
        return  ResponseEntity.ok(storeService.getStoreById(id));
    }

//    hasRole
    @GetMapping()
    public ResponseEntity<List<StoreDto>> getAllStore(@RequestHeader("Authorization")String jwt) throws UserException {
        return  ResponseEntity.ok(storeService.getAllStores());
    }


//    hasRole
    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(@RequestHeader("Authorization")String jwt) throws UserException {
        return  ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));
    }


//    hasRole
    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(@RequestHeader("Authorization") String jwt) throws UserException {
        return  ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id,
                                                      @RequestBody StoreDto storeDto) throws Exception {
        return ResponseEntity.ok(storeService.updateStore(id,storeDto));
    }

//    hasRole
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id   ) throws Exception {
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("store deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }


//    updating status of store
    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(@PathVariable Long id,
                                                @RequestParam StoreStatus status) throws Exception {
        return ResponseEntity.ok(storeService.moderateStore(id,status));
    }
}
