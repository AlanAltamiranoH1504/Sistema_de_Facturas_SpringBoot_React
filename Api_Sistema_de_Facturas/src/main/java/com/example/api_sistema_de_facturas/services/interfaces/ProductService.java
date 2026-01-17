package com.example.api_sistema_de_facturas.services.interfaces;

import com.example.api_sistema_de_facturas.exceptions.ResourceNotFoundException;
import com.example.api_sistema_de_facturas.models.Product;
import com.example.api_sistema_de_facturas.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProducService{
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public List<Product> list_products() {
        List<Product> list_products = (List<Product>) iProductRepository.findAll();
        return list_products;
    }

    @Override
    public Optional<Product> find_product_by_id(Long id) {
        Optional<Product> producto_to_show = iProductRepository.findById(id);
        return producto_to_show;
    }

    @Override
    public void save_product(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public Product sku_in_use(String sku) {
        Product sku_in_use = iProductRepository.sku_in_use(sku);
        return sku_in_use;
    }

    @Override
    public void delete_product(Long id) {
        Product product_to_delete = iProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El producto no fue encontrado en la base de datos"));
        iProductRepository.deleteById(product_to_delete.getId_product());
    }
}
