package com.example.demo_sp_th_2025.DAO;

import com.example.demo_sp_th_2025.modelo.Comercial;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {
    //Mapear las operaciones de la base de datos
    void create(Comercial comercial);

    List<Comercial> getAll();
    Optional<Comercial> find(int id);

    void update(Comercial comercial);

    void delete(int id);
}
