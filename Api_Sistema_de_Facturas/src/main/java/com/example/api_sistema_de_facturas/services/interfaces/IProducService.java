package com.example.api_sistema_de_facturas.services.interfaces;

import com.example.api_sistema_de_facturas.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProducService {
    public abstract List<Product> list_products();
    public abstract Optional<Product> find_product_by_id(Long id);
    public abstract void save_product(Product product);
    public abstract Product sku_in_use(String sku);
    public abstract void delete_product(Long id);
}
