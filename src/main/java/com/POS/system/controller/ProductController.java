package com.POS.system.controller;

import com.POS.system.Model.User;
import com.POS.system.payload.dto.ProductDto;
import com.POS.system.payload.response.ApiResponse;
import com.POS.system.service.ProductService;
import com.POS.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
                                                    @RequestHeader("Authorization") String token) throws Exception {
        User user=userService.getUserFromJwtToken(token);

        ProductDto product=productService.createProduct(productDto,user);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/{storeId}")
    public ResponseEntity<List<ProductDto>> getProductByStoreId(@PathVariable Long storeId,
                                                                @RequestHeader("Authorization") String token) throws Exception {
        List<ProductDto> products=productService.getProductByStoreId(storeId);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct( @PathVariable Long id,
                                                     @RequestBody ProductDto productDto,
                                                    @RequestHeader("Authorization") String token) throws Exception {
        User user=userService.getUserFromJwtToken(token);
        ProductDto product=productService.updateProduct(id,productDto,user);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search/{storeId}")
    public ResponseEntity<List<ProductDto>> searchByKeyword(@PathVariable Long storeId,
                                                                @RequestParam String keyword,
                                                                @RequestHeader("Authorization") String token) throws Exception {
        List<ProductDto> products=productService.searchByKeyword(storeId,keyword);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping ("/id")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id,
                                                     @RequestHeader("Authorization") String token) throws Exception {
        User user=userService.getUserFromJwtToken(token);
        ProductDto product=productService.deleteProduct(id,user);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Product deleted successfully");
        return ResponseEntity.ok(apiResponse);

    }
}
