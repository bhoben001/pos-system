package com.POS.system.repository;

import com.POS.system.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdminId(Long adminId);
}
