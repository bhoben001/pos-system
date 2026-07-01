package com.POS.system.service;

import com.POS.system.Model.User;
import com.POS.system.payload.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto product, User user) throws Exception;
    ProductDto updateProduct(Long id, ProductDto product, User user) throws Exception;
    ProductDto deleteProduct(Long id, User user) throws Exception;
    List<ProductDto> getProductByStoreId(Long id);
    List<ProductDto> searchByKeyword(Long storeId, String keyword);

    List<ProductDto> getProductByCategory(Long id, User user);


}
