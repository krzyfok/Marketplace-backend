package com.example.marketplace.product.service;

import com.example.marketplace.order.domain.Order;
import com.example.marketplace.order.dto.GetUserOrdersResponseDto;
import com.example.marketplace.product.domain.Product;
import com.example.marketplace.product.dto.GetProductsResponseDto;
import com.example.marketplace.product.dto.NewProductRequestDto;
import com.example.marketplace.product.dto.NewProductResponseDto;
import com.example.marketplace.product.dto.ProductDto;
import com.example.marketplace.product.infrastructure.ProductRepository;
import com.example.marketplace.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public GetProductsResponseDto getProducts()
    {
        List<Product> products = productRepo.findAll();
        List<ProductDto> productsDtos = products.stream().map(productMapper::mapToProductDto).toList();

        return new GetProductsResponseDto(productsDtos);

    }


}
