package com.example.api_sistema_de_facturas.repositories;

import com.example.api_sistema_de_facturas.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.sku =:sku")
    public Product sku_in_use(@Param("sku") String sku);
}
