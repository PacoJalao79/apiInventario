package com.example.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.inventario.service.InventarioService;

public class InventarioController {

     @Autowired
    private InventarioService service;

    @GetMapping("/{idProducto}")
    public ResponseEntity<Integer> obtenerStock(@PathVariable Integer idProducto) {
        int stock = service.obtenerStockPorIdProducto(idProducto);
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/{idProducto}")
    public ResponseEntity<Void> asignarStock(
        @PathVariable Integer idProducto,
        @RequestParam int cantidad
    ) {
        service.asignarStock(idProducto, cantidad);
        return ResponseEntity.ok().build();
    }

}

