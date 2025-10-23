package com.example.demo_sp_th_2025.modelos;

import lombok.Data;

import java.sql.Date;

@Data
public class Pedido {
    private int id;
    private double total;
    private Date fecha;
    private int idCliente;
    private int idComercial;

}

