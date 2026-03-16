package com.example.marketplace.product.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewProductResponseDto {
    private int returnCode;

    public NewProductResponseDto(int returnCode) {
        this.returnCode = returnCode;
    }

}
