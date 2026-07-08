package com.POS.system.repository;

import com.POS.system.Domain.UserRole;
import com.POS.system.Model.Store;
import com.POS.system.Model.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(@Email String email);
    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
