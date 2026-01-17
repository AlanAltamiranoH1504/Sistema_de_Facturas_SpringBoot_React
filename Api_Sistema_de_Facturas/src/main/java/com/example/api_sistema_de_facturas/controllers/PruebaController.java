package com.example.api_sistema_de_facturas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/prueba")
public class PruebaController {

    @GetMapping("")
    public ResponseEntity<?> metodo_prueba() {
        try {
            Map<String, Object> json = new HashMap<>();

            json.put("status", true);
            json.put("message", "Funcionando controlador de pruebas");

            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
