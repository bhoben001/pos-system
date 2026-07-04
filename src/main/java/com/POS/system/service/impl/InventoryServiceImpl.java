package com.POS.system.service.impl;

import com.POS.system.Mapper.InventoryMapper;
import com.POS.system.Model.Branch;
import com.POS.system.Model.Inventory;
import com.POS.system.Model.Product;
import com.POS.system.payload.dto.InventoryDto;
import com.POS.system.repository.BranchRepository;
import com.POS.system.repository.InventoryRepository;
import com.POS.system.repository.ProductRepository;
import com.POS.system.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {
        Branch branch=branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(()->new Exception("branch does not exist"));
        Product product=productRepository.findById(inventoryDto.getProductId()).orElseThrow(()->new Exception("product does not exist"));
        Inventory inventory= InventoryMapper.toEntity(inventoryDto,branch,product);
        Inventory savedInventory= inventoryRepository.save(inventory);
        return InventoryMapper.toDto(savedInventory);
    }

    @Override
    public InventoryDto updateInventory(Long id,InventoryDto inventoryDto) throws Exception {
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(()->new Exception("inventory not found"));
        if(inventoryDto.getQuantity()!=null)inventory.setQuantity(inventoryDto.getQuantity());

        Inventory updatedInventory=inventoryRepository.save(inventory);
    return InventoryMapper.toDto(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(()->new Exception("inventory not found"));
        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(()->new Exception("inventory not found"));

        return InventoryMapper.toDto(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory=inventoryRepository.findByProductIdAndBranchId(productId,branchId);
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventories=inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::toDto).collect(Collectors.toList());
    }
}
