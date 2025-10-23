package com.example.demo_sp_th_2025.DAO;

import com.example.demo_sp_th_2025.modelo.Cliente;

import java.util.List;
import java.util.Optional;

public class CliendaoDAOImpl implements ClienteDAO {

    public CliendaoDAOImpl() {
        super();
    }

    @Override
    public void create(Cliente cliente) {

    }

    @Override
    public List<Cliente> getAll() {
        return List.of();

    }

    @Override
    public Optional<Cliente> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void delete(int id) {

    }
}
