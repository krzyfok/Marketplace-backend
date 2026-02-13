package com.example.marketplace.Product.domain;

public enum ProductCategory {
    ELECTRONICS("TYPE_ELECTRONICS");
    private  final String value;
    ProductCategory(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
