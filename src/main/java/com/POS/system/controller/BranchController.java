package com.POS.system.controller;

import com.POS.system.payload.dto.BranchDto;
import com.POS.system.payload.response.ApiResponse;
import com.POS.system.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto>  createBranch(@RequestBody BranchDto branchDto){
        BranchDto branch= branchService.createBranch(branchDto);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto>  getBranchById( @PathVariable Long id) throws Exception {
        BranchDto branch= branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<BranchDto>>  getAllBranchByStoreId( @PathVariable Long id){
       List<BranchDto> branches= branchService.getAllBranchByStoreId(id);
        return ResponseEntity.ok(branches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDto>  updateBranch( @PathVariable Long id, @RequestBody BranchDto branchDto) throws Exception {
        BranchDto updated= branchService.updateBranch(id, branchDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>  deleteBranch(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted branch");
        return ResponseEntity.ok(apiResponse);

    }
}
