package com.example.inventario.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementable
    @Column(name = "id_producto")
    private Integer id_Inv;

    private Integer idProducto;
    private int stock;
    private String ubicacion;

}

