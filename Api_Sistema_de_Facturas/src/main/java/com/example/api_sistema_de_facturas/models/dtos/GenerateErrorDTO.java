package com.example.api_sistema_de_facturas.models.dtos;

public class GenerateErrorDTO {
    private String error;
    private String message;

    public GenerateErrorDTO(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
