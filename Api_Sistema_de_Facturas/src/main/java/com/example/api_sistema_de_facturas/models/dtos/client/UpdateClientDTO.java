package com.example.api_sistema_de_facturas.models.dtos.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UpdateClientDTO {
    @NotEmpty(message = "El nombre del cliente es obligatorio")
    @Size(max = 150, message = "El nombre no puede ser mayor a 150 caracteres")
    private String name;

    @NotEmpty(message = "Los apellidos son obligatorios")
    @Size(min = 5, max = 150, message = "La longitud de apellidos no puede ser mayor a 150 caracteres")
    private String surnames;

    @NotEmpty(message = "El email es obligatorio")
    @Email(message = "Formato de email no valido")
    private String email;

    @Size(max = 400, message = "La direcion no puede ser de mas de 400 caracteres")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
