package com.example.marketplace.product.service;

import com.example.marketplace.product.domain.Product;
import com.example.marketplace.product.dto.NewProductRequestDto;
import com.example.marketplace.product.dto.NewProductResponseDto;
import com.example.marketplace.product.infrastructure.ProductRepository;
import com.example.marketplace.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepo,ProductMapper productMapper)
    {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    @Transactional
    public NewProductResponseDto createProduct(NewProductRequestDto request)
    {
        Product newProduct = productMapper.mapToProduct(request);
        productRepo.save(newProduct);
        return new NewProductResponseDto(0);
    }


}
