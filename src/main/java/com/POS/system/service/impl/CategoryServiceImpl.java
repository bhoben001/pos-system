package com.POS.system.service.impl;

import com.POS.system.Domain.UserRole;
import com.POS.system.Mapper.CategoryMapper;
import com.POS.system.Model.Category;
import com.POS.system.Model.Product;
import com.POS.system.Model.Store;
import com.POS.system.Model.User;
import com.POS.system.payload.dto.CategoryDto;
import com.POS.system.repository.CategoryRepository;
import com.POS.system.repository.ProductRepository;
import com.POS.system.repository.StoreRepository;
import com.POS.system.service.CategoryService;
import com.POS.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
        User user=userService.getCurrentUser();
        Store store=storeRepository.findById(categoryDto.getStoreId()).orElseThrow(()->new Exception("Store now found"));

        Category category=new Category();
            category.setStore(store);
            category.setCategoryName(categoryDto.getCategoryName());

        checkAuthority(user,category.getStore());
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        List<Category> categories=categoryRepository.findByStoreId(storeId);
        return categories.stream().map(CategoryMapper::toDto).collect(Collectors.toList());
    }

    @Override
//    here id is category id
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception {

        Category category=categoryRepository.findById(id).orElseThrow(()->new Exception("category does not exist"));
        User user=userService.getCurrentUser();

        category.setCategoryName(categoryDto.getCategoryName());

        checkAuthority(user,category.getStore());
        Category updatedCategory=categoryRepository.save(category);
        return CategoryMapper.toDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(()->new Exception("category does not exist"));

        User user=userService.getCurrentUser();
        checkAuthority(user,category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin=user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager=user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore=user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager ){
            throw new Exception("you don't have permission to manage the category ");
        }
    }
}
