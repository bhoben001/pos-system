package com.POS.system.service;

import com.POS.system.payload.dto.BranchDto;

import java.util.List;

public interface BranchService {
    BranchDto createBranch(BranchDto branchDto);
    BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDto> getAllBranchByStoreId(Long StoreId);
    BranchDto getBranchById(Long id) throws Exception;
}
