package com.example.api_sistema_de_facturas.controllers;

import com.example.api_sistema_de_facturas.models.Client;
import com.example.api_sistema_de_facturas.models.dtos.GenerateErrorDTO;
import com.example.api_sistema_de_facturas.models.dtos.client.SaveClientDTO;
import com.example.api_sistema_de_facturas.models.dtos.client.UpdateClientDTO;
import com.example.api_sistema_de_facturas.services.interfaces.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private IClientService iClientService;

    @GetMapping("/list_clients")
    public ResponseEntity<?> list_clients() {
        try {
            List<Client> list_clients = iClientService.list_clients();
            if (list_clients.isEmpty()) {
                Map<String, Object> json = new HashMap<>();
                json.put("status", false);
                json.put("message", "La lista de clientes esta vacia");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(list_clients);
        } catch (Exception e) {
            GenerateErrorDTO generateErrorDTO = new GenerateErrorDTO(e.getMessage(), "Ocurio un error en el listado de clientes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateErrorDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find_by_id(@PathVariable Long id) {
        try {
            Map<String, Object> json = new HashMap<>();
            Optional<Client> client_to_show = iClientService.find_client_by_id(id);
            if (client_to_show.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(client_to_show);
            } else {
                json.put("status", false);
                json.put("message", "Cliente con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (Exception e) {
            GenerateErrorDTO generateErrorDTO = new GenerateErrorDTO(e.getMessage(), "Ocurio un error en la busqueda del cliente");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateErrorDTO);
        }
    }

    @PostMapping("save_client")
    public ResponseEntity<?> save_client(@Valid @RequestBody SaveClientDTO clientDTO, BindingResult bindingResult) {
        try {
            Map<String, Object> json = new HashMap<>();
            if (bindingResult.hasErrors()) {
                bindingResult.getFieldErrors().forEach(error -> {
                    json.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
            }

            Client email_in_use = iClientService.email_in_use(clientDTO.getEmail());
            if (email_in_use != null) {
                json.put("status", false);
                json.put("message", "El email ya se encuentra en uso");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }

            Client client_to_save = new Client(clientDTO.getName(), clientDTO.getSurnames(), clientDTO.getEmail(), clientDTO.getAddress());
            boolean result_save_client = iClientService.save_client(client_to_save);
            if (result_save_client) {
                json.put("status", true);
                json.put("message", "Cliente Guardado Correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            }
            GenerateErrorDTO generateErrorDTO = new GenerateErrorDTO("Error en guardado de cliente", "Ocurrio un error al almacenar un nuevo cliente");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(generateErrorDTO);

        } catch (Exception e) {
            GenerateErrorDTO generateErrorDTO = new GenerateErrorDTO(e.getMessage(), "Ocurio un error en el actualizado de cliente por parte del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateErrorDTO);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update_client(@PathVariable Long id, @Valid @RequestBody UpdateClientDTO updateClientDTO, BindingResult bindingResult) {
        try {
            Map<String, Object> json = new HashMap<>();
            if (bindingResult.hasErrors()) {
                bindingResult.getFieldErrors().forEach(error -> {
                    json.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
            }
            Client email_in_use = iClientService.email_in_use(updateClientDTO.getEmail());
            if (email_in_use != null && email_in_use.getId_client() != id) {
                json.put("status", false);
                json.put("message", "El email ya se encuentra en uso por otro cliente");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }
            Client client_to_update = new Client(id, updateClientDTO.getName(), updateClientDTO.getSurnames(), updateClientDTO.getEmail(), updateClientDTO.getAddress());
            boolean result_update_client = iClientService.save_client(client_to_update);
            if (result_update_client) {
                json.put("status", true);
                json.put("message", "Cliente actualizado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } else {
                json.put("status", false);
                json.put("message", "Error en actualizacion de cliente");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }
        } catch (Exception e) {
            GenerateErrorDTO generateErrorDTO = new GenerateErrorDTO(e.getMessage(), "Ocurio un error en el guardo de cliente por parte del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateErrorDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete_client(@PathVariable Long id) {
        try {
            Map<String, Object> json = new HashMap<>();
            boolean result_delete_client = iClientService.delete_by_id(id);
            if (result_delete_client) {
                json.put("status", true);
                return ResponseEntity.status(HttpStatus.OK).body(json);
            }
            json.put("status", false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
        } catch (Exception e) {
            GenerateErrorDTO generateErrorDTO = new GenerateErrorDTO(e.getMessage(), "Ocurio un error en el eliminado de cliente por parte del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateErrorDTO);
        }
    }
}
