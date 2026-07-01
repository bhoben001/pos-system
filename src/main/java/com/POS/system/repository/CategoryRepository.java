package com.POS.system.repository;

import com.POS.system.Model.Category;
import com.POS.system.payload.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStoreId(Long storeId);
}
