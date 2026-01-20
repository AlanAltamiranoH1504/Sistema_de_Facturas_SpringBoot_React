package com.example.api_sistema_de_facturas.repositories;

import com.example.api_sistema_de_facturas.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IClientRepository extends CrudRepository<Client, Long> {
    @Query("SELECT c FROM  Client c WHERE c.email =:email")
    public Client email_in_use(@Param("email") String email);
}
