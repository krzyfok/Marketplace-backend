package com.example.marketplace.Product.service;

import com.example.marketplace.Product.domain.Product;
import com.example.marketplace.Product.dto.NewProductRequestDto;
import com.example.marketplace.Product.dto.NewProductResponseDto;
import com.example.marketplace.Product.infrastructure.ProductRepository;
import com.example.marketplace.Product.mapper.ProductMapper;
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

        Product newProduct = productMapper.mapFromNewProductRequestDtoToProduct(request);
        productRepo.save(newProduct);
        return new NewProductResponseDto(0);
    }


}
