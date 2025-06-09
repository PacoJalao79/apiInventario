package com.example.inventario.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {
    
    private Integer id_Inv;
    private int stock;
    private String ubicacion;
}


