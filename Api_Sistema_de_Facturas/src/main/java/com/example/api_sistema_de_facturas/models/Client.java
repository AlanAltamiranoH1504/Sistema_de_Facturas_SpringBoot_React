package com.example.api_sistema_de_facturas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "tbl_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_client;
    private String name;
    private String surnames;
    private String email;
    private String address;

    @JsonManagedReference
    @OneToMany(orphanRemoval = true, mappedBy = "client")
    private List<Invoice> invoices;

    public Client() {

    }

    public Client(String name, String surnames, String email, String address) {
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.address = address;
    }

    public Client(long id_client, String name, String surnames, String email, String address) {
        this.id_client = id_client;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.address = address;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

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
