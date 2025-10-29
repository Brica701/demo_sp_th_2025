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

}
