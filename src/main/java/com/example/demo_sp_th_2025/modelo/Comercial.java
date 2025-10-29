package com.example.demo_sp_th_2025.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class Comercial {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private BigDecimal comision;

}
