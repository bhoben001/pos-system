package com.POS.system.service;

import com.POS.system.Domain.UserRole;
import com.POS.system.Model.User;
import com.POS.system.payload.dto.UserDto;

import java.util.List;

public interface EmployeeService {
        UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception;
        UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception;
        User updateEmployee(Long employeeId,UserDto employeeDetails) throws Exception;
        void deleteEmployee(Long employeeId) throws Exception;
        List<UserDto> findStoreEmployee(Long storeId,UserRole role) throws Exception;
        List<UserDto> findBranchEmployee(Long branchId, UserRole role) throws Exception;
}
