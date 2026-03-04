package com.example.marketplace.Product.dto;

public class NewProductResponseDto {
    private int returnCode;

    public int getReturnCode() {
        return returnCode;
    }

    public NewProductResponseDto(int returnCode) {
        this.returnCode = returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
