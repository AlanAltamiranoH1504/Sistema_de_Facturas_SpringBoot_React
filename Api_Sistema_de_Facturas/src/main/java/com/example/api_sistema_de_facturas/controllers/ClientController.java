package com.example.api_sistema_de_facturas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @PostMapping("save_client")
    public ResponseEntity<?> save_client() {
        try {
            Map<String, Object> json = new HashMap<>();
            json.put("status", true);
            json.put("message", "S001");

            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
