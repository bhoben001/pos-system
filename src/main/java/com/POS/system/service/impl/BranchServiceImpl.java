package com.POS.system.service.impl;

import com.POS.system.Mapper.BranchMapper;
import com.POS.system.Model.Branch;
import com.POS.system.Model.Store;
import com.POS.system.payload.dto.BranchDto;
import com.POS.system.repository.BranchRepository;
import com.POS.system.repository.StoreRepository;
import com.POS.system.service.BranchService;
import com.POS.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;


    @Override
    public BranchDto createBranch(BranchDto branchDto) {
        Store store=storeRepository.findByStoreAdminId(userService.getCurrentUser().getId());
        Branch branch= BranchMapper.toEntity(branchDto,store);
        branchRepository.save(branch);
        return BranchMapper.toDto(branch);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto dto) throws Exception {
        Branch branch=branchRepository.findById(id).orElseThrow(()->new Exception("branch does not exist"));
        if(dto.getName() !=null)branch.setName(dto.getName());
        if(dto.getAddress() !=null)branch.setAddress(dto.getAddress());
        if(dto.getPhone() !=null)branch.setPhone(dto.getPhone());
        if(dto.getEmail() !=null)branch.setEmail(dto.getEmail());
        if(dto.getWorkingDays() !=null)branch.setWorkingDays(dto.getWorkingDays());
        if(dto.getOpenTime() !=null)branch.setOpenTime(dto.getOpenTime());
        if(dto.getCloseTime() !=null)branch.setOpenTime(dto.getOpenTime());
        Branch saved=branchRepository.save(branch);
        return BranchMapper.toDto(saved);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        branchRepository.findById(id).orElseThrow(()->new Exception("branch does not exist"));
        branchRepository.deleteById(id);
    }

    @Override
    public List<BranchDto> getAllBranchByStoreId(Long StoreId) {
         List<Branch> branches=branchRepository.findAllByStoreId(StoreId);
         return branches.stream().map(BranchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BranchDto getBranchById(Long id) throws Exception {
        Branch branch=branchRepository.findById(id).orElseThrow(()->new Exception("branch does not exist"));
        return BranchMapper.toDto(branch);
    }
}
