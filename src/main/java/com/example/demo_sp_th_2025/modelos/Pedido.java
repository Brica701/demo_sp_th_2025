package com.example.demo_sp_th_2025.modelos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Pedido {
    private int id;
    private BigDecimal total;
    private LocalDate fecha;
    private Integer idCliente;
    private Integer idComercial;

}

