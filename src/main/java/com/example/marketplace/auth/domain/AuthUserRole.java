package com.example.marketplace.auth.domain;

public enum AuthUserRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private  final String value;

    AuthUserRole(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }


}
