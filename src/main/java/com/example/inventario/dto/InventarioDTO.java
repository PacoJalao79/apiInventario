package com.example.inventario.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO extends RepresentationModel<InventarioDTO>{
    
    private Integer id_Inv;
    private int stock;
    private String ubicacion;
}


