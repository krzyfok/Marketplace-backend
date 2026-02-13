package com.example.marketplace.Product.service;

import com.example.marketplace.Product.domain.Product;
import com.example.marketplace.Product.dto.NewProductRequest;
import com.example.marketplace.Product.dto.NewProductResponse;
import com.example.marketplace.Product.infrastructure.ProductRepository;
import org.springframework.security.core.parameters.P;
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
    public NewProductResponse createProduct(NewProductRequest request)
    {

        Product newProduct = new Product(request.getName(), request.getModel(), request.getCategory(), request.getPrice());
        productRepo.save(newProduct);
        return new NewProductResponse(0);
    }


}
