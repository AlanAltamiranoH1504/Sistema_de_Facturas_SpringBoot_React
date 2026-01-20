package com.example.api_sistema_de_facturas.services.interfaces;

import com.example.api_sistema_de_facturas.models.Client;
import com.example.api_sistema_de_facturas.repositories.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientRepository iClientRepository;

    @Override
    public List<Client> list_clients() {
        List<Client> list_clients = (List<Client>) iClientRepository.findAll();
        return list_clients;
    }

    @Override
    public Optional<Client> find_client_by_id(Long id) {
        Optional<Client> client_to_show = iClientRepository.findById(id);
        return client_to_show;
    }

    @Override
    public boolean save_client(Client client) {
        try {
            iClientRepository.save(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete_by_id(Long id) {
        Optional<Client> client_to_delete = iClientRepository.findById(id);
        if (client_to_delete.isPresent()) {
            iClientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Client email_in_use(String email) {
        Client email_in_use = iClientRepository.email_in_use(email);
        return email_in_use;
    }
}
