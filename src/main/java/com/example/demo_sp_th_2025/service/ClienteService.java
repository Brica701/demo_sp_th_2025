package com.example.demo_sp_th_2025.service;

import com.example.demo_sp_th_2025.DAO.ClienteDAO;
import com.example.demo_sp_th_2025.modelo.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

//Anotacion importante
@Service
//@AllArgsConstructor

public class ClienteService {
    //Implementar la lógica de negocio.
    private ClienteDAO clienteDAO;

    //Inyeccion de dependencias con constructor de clienteDAO.
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> all(){
        return clienteDAO.getAll();
    }
}

