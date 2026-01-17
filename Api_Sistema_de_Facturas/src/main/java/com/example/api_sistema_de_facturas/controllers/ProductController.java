package com.example.api_sistema_de_facturas.controllers;

import com.example.api_sistema_de_facturas.models.Product;
import com.example.api_sistema_de_facturas.models.dtos.GenerateErrorDTO;
import com.example.api_sistema_de_facturas.models.dtos.product.SaveProductDTO;
import com.example.api_sistema_de_facturas.models.dtos.product.UpdateProductDto;
import com.example.api_sistema_de_facturas.services.interfaces.IProducService;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProducService iProducService;

    @GetMapping("")
    public ResponseEntity<?> list_products() {
        List<Product> list_products = iProducService.list_products();
        if (list_products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(list_products);
    }

    @PostMapping("/save_product")
    public ResponseEntity<?> save_product(@Valid @RequestBody SaveProductDTO productDTO, BindingResult bindingResult) {
        try {
            Map<String, Object> json = new HashMap<>();
            if (bindingResult.hasErrors()) {
                Map<String, Object> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }

            Product product_to_save = new Product(productDTO.getName_product(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getSku());
            iProducService.save_product(product_to_save);

            json.put("status", true);
            json.put("message", "Producto guardado correctamente");

            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            GenerateErrorDTO errorDTO = new GenerateErrorDTO(e.getMessage(), "Error en guardado de producto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }

    @GetMapping("/find_product_by_id/{id}")
    public ResponseEntity<?> find_product_by_id(@PathVariable Long id) {
        try {
            Optional<Product> producto_to_show = iProducService.find_product_by_id(id);

            if (producto_to_show.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(producto_to_show.get());
            } else {
                Map<String, Object> json = new HashMap<>();
                json.put("status", false);
                json.put("message", "Producto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (Exception e) {
            GenerateErrorDTO errorDTO = new GenerateErrorDTO(e.getMessage(), "Error en busqueda de producto con id " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }

    @PutMapping("/update_product/{id}")
    public ResponseEntity<?> update_product(@PathVariable Long id, @Valid @RequestBody UpdateProductDto updateProductDto, BindingResult bindingResult) {
        try {
            Map<String, Object> json = new HashMap<>();
            if (bindingResult.hasErrors()) {
                Map<String, Object> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }
            Product sku_in_use = iProducService.sku_in_use(updateProductDto.getSku());
            if (sku_in_use != null && !sku_in_use.getId_product().equals(id)) {
                json.put("status", false);
                json.put("message", "El sku ya se encuentra en uso");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }

            //Busqueda y actualizacion
            Optional<Product> producto_to_update = iProducService.find_product_by_id(id);
            if (producto_to_update.isPresent()) {
                Product product = new Product(id, updateProductDto.getName_product(), updateProductDto.getDescription(), updateProductDto.getPrice(), updateProductDto.getSku());
                iProducService.save_product(product);
                json.put("status", true);
                json.put("message", "Producto actualizado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } else {
                json.put("status", false);
                json.put("message", "Producto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (Exception e) {
            GenerateErrorDTO errorDTO = new GenerateErrorDTO(e.getMessage(), "Error en actualizacion de producto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }

    @DeleteMapping("/delete_product/{id}")
    public ResponseEntity<?> delete_product(@PathVariable Long id) {
        iProducService.delete_product(id);
        Map<String, Object> json = new HashMap<>();
        json.put("status", true);
        json.put("message", "Producto eliminado correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
