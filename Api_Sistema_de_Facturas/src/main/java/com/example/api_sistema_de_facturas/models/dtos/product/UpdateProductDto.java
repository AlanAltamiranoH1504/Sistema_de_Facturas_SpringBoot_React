package com.example.api_sistema_de_facturas.models.dtos.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateProductDto {
    @NotEmpty(message = "El nombre del producto es obligatorio")
    @Size(min = 1, max = 255, message = "Longitud de nombre no valida. Máximo 255 caracteres")
    private String name_product;

    @NotEmpty(message = "La descripcion del producto es obligatoria")
    @Size(min = 1, max = 500, message = "La longitud de la descripcion no es valida. Máximo 500 caracteres")
    private String description;

    @Positive(message = "Precio no valido")
    private double price;

    @NotEmpty(message = "El sku del producto es obligatorio")
    @Size(min = 4, max = 20, message = "La longitud del sku no es valida. Máximo 20 caracteres y minimo 4")
    private String sku;

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
