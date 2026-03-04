package com.example.marketplace.Product.service;

import com.example.marketplace.Product.domain.Product;
import com.example.marketplace.Product.dto.NewProductRequestDto;
import com.example.marketplace.Product.dto.NewProductResponseDto;
import com.example.marketplace.Product.infrastructure.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo)
    {
        this.productRepo = productRepo;
    }

    @Transactional
    public NewProductResponseDto createProduct(NewProductRequestDto request)
    {

        Product newProduct = new Product(request.getName(), request.getModel(), request.getCategory(), request.getPrice());
        productRepo.save(newProduct);
        return new NewProductResponseDto(0);
    }


}
