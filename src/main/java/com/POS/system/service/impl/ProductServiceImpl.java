package com.POS.system.service.impl;

import com.POS.system.Mapper.CategoryMapper;
import com.POS.system.Mapper.ProductMapper;
import com.POS.system.Model.Category;
import com.POS.system.Model.Product;
import com.POS.system.Model.Store;
import com.POS.system.Model.User;
import com.POS.system.payload.dto.ProductDto;
import com.POS.system.repository.CategoryRepository;
import com.POS.system.repository.ProductRepository;
import com.POS.system.repository.StoreRepository;
import com.POS.system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store=storeRepository.findById(productDto.getStoreId()).orElseThrow(()->new Exception("Store not found"));
        Category category=categoryRepository.findById(productDto.getCategoryId()).orElseThrow(()->new Exception("Category not found"));
        Product product= ProductMapper.toEntity(productDto,store,category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {

        if(productDto.getCategoryId()!=null) {
            Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new Exception("Category not found"));
            productDto.setCategoryDto(CategoryMapper.toDto(category));
        }


        Product product = productRepository.findById(id).orElseThrow(()->new Exception("product not found"));
        if(productDto.getName()!=null) product.setName(productDto.getName());
        if(productDto.getDescription()!=null) product.setDescription(productDto.getDescription());
        if(productDto.getSku()!=null) product.setSku(productDto.getSku());
        if(productDto.getImage() !=null) product.setImage(productDto.getImage());
        if(productDto.getMrp()!=null) product.setMrp(productDto.getMrp());
        if(productDto.getSellingPrice()!=null) product.setSellingPrice(productDto.getSellingPrice());
        if(productDto.getBrand()!=null) product.setBrand(productDto.getBrand());

        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(()->new Exception("product not found"));
        productRepository.delete(product);
        return null;
    }

    @Override
    public List<ProductDto> getProductByStoreId(Long id) {
        List<Product> products = productRepository.findByStoreId(id);
        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepository.searchByKeyword(storeId, keyword);
        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

}
