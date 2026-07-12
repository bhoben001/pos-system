package com.POS.system.service.impl;

import com.POS.system.Domain.UserRole;
import com.POS.system.Mapper.UserMapper;
import com.POS.system.Model.Branch;
import com.POS.system.Model.Store;
import com.POS.system.Model.User;
import com.POS.system.payload.dto.UserDto;
import com.POS.system.repository.BranchRepository;
import com.POS.system.repository.StoreRepository;
import com.POS.system.repository.UserRepository;
import com.POS.system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("Store not found"));
        Branch branch=null;
        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            if(employee.getBranchId()==null) throw new  Exception("Branch id is required to create branch Manager");
            branch=branchRepository.findById(employee.getBranchId()).orElseThrow(() -> new Exception("Branch not found"));
        }

        User user= UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedUser = userRepository.save(user);
        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER && branch!=null){
            branch.setManager(savedUser);
            branchRepository.save(branch);
        }

        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {
        Branch branch=branchRepository.findById(branchId).orElseThrow(() -> new Exception("Branch not found"));
        if(employee.getRole()==UserRole.ROLE_BRANCH_CASHIER||employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            User user=UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDto(userRepository.save(user));
        }

        throw new Exception("branch role not supported");
    }

    @Override
    public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
        User existingEmployee=userRepository.findById(employeeId).orElseThrow(()-> new Exception("employee does not exist with given id"));

        Branch branch=branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(() -> new Exception("Branch not found"));

        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);
        User savedUser = userRepository.save(existingEmployee);
        return savedUser;
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User user=userRepository.findById(employeeId).orElseThrow(()->new Exception("employee not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> findStoreEmployee(Long storeId,UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("Store not found"));
        return userRepository.findByStore(store).stream().filter(
                user->role==null ||user.getRole()==role).map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findBranchEmployee(Long branchId, UserRole role) throws Exception {
        Branch branch=branchRepository.findById(branchId).orElseThrow(() -> new Exception("Branch not found"));
        return userRepository.findByBranchId(branchId).stream().filter(
                user->role==null ||user.getRole()==role).map(UserMapper::toDto).collect(Collectors.toList());
    }
}
