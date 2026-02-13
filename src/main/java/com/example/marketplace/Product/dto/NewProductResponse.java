package com.example.marketplace.Product.dto;

public class NewProductResponse {
    private int returnCode;

    public int getReturnCode() {
        return returnCode;
    }

    public NewProductResponse(int returnCode) {
        this.returnCode = returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
