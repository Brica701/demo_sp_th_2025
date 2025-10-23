package com.example.demo_sp_th_2025.DAO;

import com.example.demo_sp_th_2025.modelo.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {

    //Mapear las operaciones de la base de datos
    void create(Cliente cliente);

    List<Cliente> getAll();
    Optional<Cliente> find(int id);

    void update(Cliente cliente);

    void delete(int id);

}