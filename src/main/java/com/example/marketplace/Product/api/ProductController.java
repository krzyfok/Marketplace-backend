package com.example.marketplace.Product.api;

import com.example.marketplace.Product.dto.NewProductRequestDto;
import com.example.marketplace.Product.dto.NewProductResponseDto;
import com.example.marketplace.Product.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<NewProductResponseDto> createProduct(@RequestBody NewProductRequestDto request)
    {
        try {
            return ResponseEntity.ok(productService.createProduct(request));
        }catch (DataIntegrityViolationException e)
        {
            return  ResponseEntity.badRequest().body(new NewProductResponseDto(1));
        }
    }

}
