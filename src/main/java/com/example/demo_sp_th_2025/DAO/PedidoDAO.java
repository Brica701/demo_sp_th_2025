package com.example.demo_sp_th_2025.DAO;

import com.example.demo_sp_th_2025.modelo.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {
    //Mapear las operaciones de la base de datos
    void create(Pedido pedido);

    List<Pedido> getAll();
    Optional<Pedido> find(int id);

    void update(Pedido pedido);

    void delete(int id);
}
