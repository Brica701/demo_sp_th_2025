package com.example.demo_sp_th_2025;

import com.example.demo_sp_th_2025.DAO.ClienteDAO;
import com.example.demo_sp_th_2025.modelo.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoSpTh2025ApplicationTests {

    //Spring es un framework de inyeccion de dependencias e inversion de control

    @Autowired
    private ClienteDAO clienteDAO;


    @Test
    void contextLoads() {
    }

    @Test
    void testGetAll() {
        List<Cliente> list = clienteDAO.getAll();
        list.forEach(System.out::println);
    }

    @Test
    void testFind() {
        Cliente cliente = clienteDAO.find(1).orElse(null);
        System.out.println(cliente);
    }

    @Test
    void testUpdate() {
        Cliente cliente = new Cliente(1, "Juanito", "Perez", "Perez", "Monterrey", 1);
        clienteDAO.update(cliente);
    }
}
