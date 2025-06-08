package com.example.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;



@Entity
@Table(name = "inventario")
@Data


public class InventarioModel {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    



 

}
