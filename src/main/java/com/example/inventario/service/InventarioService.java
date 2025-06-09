package com.example.inventario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inventario.dto.InventarioDTO;
import com.example.inventario.model.Inventario;
import com.example.inventario.repository.InventarioRepository;


@Service
public class InventarioService {

     @Autowired
    private InventarioRepository inventarioRepository;

    public int obtenerStockPorIdProducto(Integer idProducto) {
        return inventarioRepository.findByIdProducto(idProducto)
                .map(Inventario::getStock)
                .orElse(0); // O lanza excepción si prefieres
    }

    public void asignarStock(Integer idProducto, int cantidad) {
        Inventario inventario = inventarioRepository.findByIdProducto(idProducto)
                .orElseGet(() -> {
                    Inventario nuevo = new Inventario();
                    nuevo.setIdProducto(idProducto);
                    nuevo.setStock(0);
                    nuevo.setUbicacion("Ubicación predeterminada");
                    return nuevo;
                });

        inventario.setStock(inventario.getStock() + cantidad);
        inventarioRepository.save(inventario);
    }

    private InventarioDTO toDTO(Inventario inventario) {
        return new InventarioDTO(
                inventario.getId_Inv(),
                inventario.getStock(),
                inventario.getUbicacion()
        );
    }

    private Inventario toEntity(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setId_Inv(dto.getId_Inv()); // importante para actualizar
        inventario.setStock(dto.getStock());
        inventario.setUbicacion(dto.getUbicacion());
        return inventario;
    }

    public InventarioDTO crear(InventarioDTO dto) {
        Inventario producto = toEntity(dto);
        return toDTO(inventarioRepository.save(producto));
    }

    public List<InventarioDTO> listar() {
        return inventarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public InventarioDTO obtenerPorId(Integer id) {
        Inventario producto = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(producto);
    }

    public InventarioDTO actualizar(Integer id, InventarioDTO dto) {
        Inventario existente = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setStock(dto.getStock());
        existente.setUbicacion(dto.getUbicacion());

        return toDTO(inventarioRepository.save(existente));
    }

    public void eliminar(Integer id) {
        inventarioRepository.deleteById(id);
    }

}
