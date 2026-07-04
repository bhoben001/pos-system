package com.POS.system.controller;

import com.POS.system.payload.dto.InventoryDto;
import com.POS.system.payload.response.ApiResponse;
import com.POS.system.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) throws Exception{
        return  ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateInventory(@PathVariable Long id,@RequestBody InventoryDto inventoryDto) throws Exception{
        return  ResponseEntity.ok(inventoryService.updateInventory(id,inventoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long id) throws Exception{
        inventoryService.deleteInventory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted inventory");
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranchId(@PathVariable Long branchId) throws Exception{
        return  ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
    }

    @GetMapping("/product/{productId}/branch/{branchId}")
    public ResponseEntity<InventoryDto> getInventoryByProductAndBranchId(@PathVariable Long productId,@PathVariable Long branchId) throws Exception{
        return  ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId,branchId));
    }



}
