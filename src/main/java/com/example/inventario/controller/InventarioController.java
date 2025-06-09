package com.example.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.inventario.dto.InventarioDTO;
import com.example.inventario.service.InventarioService;


import java.util.List;


@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

     @Autowired
    private InventarioService service;

    @PostMapping("/p/{idProducto}")
    public ResponseEntity<Void> asignarStock(
        @PathVariable Integer idProducto,
        @RequestParam int cantidad
    ) {
        service.asignarStock(idProducto, cantidad);
        return ResponseEntity.ok().build();
    }

     @PostMapping
    public ResponseEntity<InventarioDTO> crear(@RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Integer id, @RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id_inv) {
        service.eliminar(id_inv);
        return ResponseEntity.noContent().build();
    }

}


