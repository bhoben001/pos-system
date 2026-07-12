package com.POS.system.controller;

import com.POS.system.Domain.UserRole;
import com.POS.system.Model.User;
import com.POS.system.payload.dto.UserDto;
import com.POS.system.payload.response.ApiResponse;
import com.POS.system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(@RequestBody UserDto userDto, @PathVariable Long storeId) throws Exception {
        UserDto employee = employeeService.createStoreEmployee(userDto,storeId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(@RequestBody UserDto userDto, @PathVariable Long branchId) throws Exception {
        UserDto employee = employeeService.createBranchEmployee(userDto,branchId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<User> updateEmployee(@PathVariable Long employeeId,@RequestBody UserDto userDto) throws Exception {
        User employee = employeeService.updateEmployee(employeeId,userDto);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long employeeId) throws Exception {
        employeeService.deleteEmployee(employeeId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("employee is deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{id}") //storeId
    public ResponseEntity<List<UserDto>> storeEmployees(@PathVariable Long id, @RequestParam(required=false) UserRole userRole) throws Exception {
        List<UserDto> employees=employeeService.findStoreEmployee(id,userRole);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/branch/{id}") //branchId
    public ResponseEntity<List<UserDto>> branchEmployees(@PathVariable Long id, @RequestParam(required=false) UserRole userRole) throws Exception {
        List<UserDto> employees=employeeService.findBranchEmployee(id,userRole);
        return ResponseEntity.ok(employees);
    }
}
