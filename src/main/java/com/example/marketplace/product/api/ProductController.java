package com.example.marketplace.product.api;

import com.example.marketplace.product.dto.GetProductsResponseDto;
import com.example.marketplace.product.dto.NewProductRequestDto;
import com.example.marketplace.product.dto.NewProductResponseDto;
import com.example.marketplace.product.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<GetProductsResponseDto> getAllProducts()
    {

        return  ResponseEntity.ok(productService.getProducts());
    }


}
