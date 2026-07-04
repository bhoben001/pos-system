package com.POS.system.repository;

import com.POS.system.Model.Inventory;
import com.POS.system.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Inventory findByProductIdAndBranchId(Long productId,Long branchId);
    List<Inventory> findByBranchId(Long branchId);
}
