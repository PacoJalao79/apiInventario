package com.example.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventario.dto.InventarioDTO;
import com.example.inventario.service.InventarioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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

    @GetMapping("/hateoas/{id}")
    public InventarioDTO obtenerHATEOAS(@PathVariable Integer id) {
        InventarioDTO dto = service.obtenerPorId(id);
        
        //links urls de la misma API
        dto.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(InventarioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(InventarioController.class).eliminar(id)).withRel("eliminar"));

        //link HATEOAS para API Gateway "A mano"
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId_Inv()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId_Inv()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId_Inv()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return dto;
    }

    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<InventarioDTO> obtenerTodosHATEOAS() {
        List<InventarioDTO> lista = service.listar();

        for (InventarioDTO dto : lista) {
            //link url de la misma API
            dto.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(dto.getId_Inv())).withSelfRel());

            //link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/productos").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId_Inv()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }

}


