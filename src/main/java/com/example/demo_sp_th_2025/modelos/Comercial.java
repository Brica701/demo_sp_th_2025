package com.example.demo_sp_th_2025.modelos;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Comercial {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private BigDecimal comision;

}
