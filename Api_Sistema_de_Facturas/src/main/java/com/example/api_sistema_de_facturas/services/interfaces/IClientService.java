package com.example.api_sistema_de_facturas.services.interfaces;

import com.example.api_sistema_de_facturas.models.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    public abstract List<Client> list_clients();
    public abstract Optional<Client> find_client_by_id(Long id);
    public abstract boolean save_client(Client client);
    public abstract boolean delete_by_id(Long id);
    public abstract Client email_in_use(String email);
}
