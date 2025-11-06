package com.example.demo_sp_th_2025;

import com.example.demo_sp_th_2025.DAO.ClienteDAO;
import com.example.demo_sp_th_2025.DAO.ComercialDAO;
import com.example.demo_sp_th_2025.modelo.Cliente;
import com.example.demo_sp_th_2025.modelo.Comercial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoSpTh2025ApplicationTests {

    //Spring es un framework de inyeccion de dependencias e inversion de control

    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private ComercialDAO comercialDAO;


    @Test
    void contextLoads() {
    }

    @Test
    void testCreate() {
       Cliente cliente = Cliente.builder()
               .nombre("bro")
               .apellido1("eres")
               .apellido2("payasa")
               .ciudad("Malaga")
               .categoria(1)
               .build();

       System.out.println("Antes de crear: " + cliente.getId());

       clienteDAO.create(cliente);
        System.out.println("Despues de crear: " + cliente.getId());
    }

    @Test
    void testGetAll() {
        List<Cliente> list = clienteDAO.getAll();
        list.forEach(System.out::println);
    }

    @Test
    void testFind() {
        Cliente cliente = clienteDAO.find(3).get();
        System.out.println(cliente);
    }

    @Test
    void testUpdate() {
        Cliente cliente = Cliente.builder()
                .nombre("Juanito")
                .apellido1("Perez")
                .apellido2("Perez")
                .ciudad("Malaga")
                .categoria(1)
                .build();
        System.out.println("Antes de crear: " + cliente.getId());
        clienteDAO.create(cliente);
        cliente.setNombre("Juanito");
        clienteDAO.update(cliente);

        Optional<Cliente> clienteOptional = clienteDAO.find(cliente.getId());

        if (clienteOptional.isPresent()) {
            Assertions.assertEquals("Juanito", clienteOptional.get().getNombre());
        } else {
            Assertions.fail("Cliente no encontrado");
        }
    }

    @Test
    void testDelete() {
        Cliente cliente = Cliente.builder()
                .nombre("Jose")
                .apellido1("Martin")
                .apellido2("Tejero")
                .ciudad("Malaga")
                .categoria(1)
                .build();
        clienteDAO.create(cliente);
        clienteDAO.delete(cliente.getId());

        Optional<Cliente> optionalCliente = clienteDAO.find(cliente.getId());
        Assertions.assertTrue(optionalCliente.isEmpty());

    }


    @Test
    void testCreateComercial() {
        Comercial comercial = Comercial.builder()
                .nombre("Jose")
                .apellido1("Martin")
                .apellido2("Tejero")
                .comision(new BigDecimal("0.03")) // 3%
                .build();

        System.out.println("Antes de crear: " + comercial.getId());
        comercialDAO.create(comercial);
        System.out.println("Después de crear: " + comercial.getId());
    }

    @Test
    void testGetAllComercial() {
        List<Comercial> list = comercialDAO.getAll();
        list.forEach(System.out::println);
    }

    @Test
    void testFindComercial() {
        Comercial comercial = comercialDAO.find(3).get();
        System.out.println(comercial);
    }

    @Test
    void testUpdateComercial() {
        Comercial comercial = Comercial.builder()
                .nombre("Jose")
                .apellido1("Martin")
                .apellido2("Tejero")
                .comision(new BigDecimal("0.03")) // 3%
                .build();
        System.out.println("Antes de crear: " + comercial.getId());
        comercialDAO.create(comercial);
        comercial.setNombre("Juanito");
        comercialDAO.update(comercial);

        Optional<Comercial> comercialOptional = comercialDAO.find(comercial.getId());

        if (comercialOptional.isPresent()) {
            Assertions.assertEquals("Juanito", comercialOptional.get().getNombre());
        } else {
            Assertions.fail("Comercial no encontrado");
        }
    }


    @Test
    void testDeleteComercial() {

        Comercial comercial = Comercial.builder()
                .nombre("Jose")
                .apellido1("Martin")
                .apellido2("Tejero")
                .comision(new BigDecimal("0.03")) // 3%
                .build();
        comercialDAO.create(comercial);
        comercialDAO.delete(comercial.getId());

        Optional<Comercial> comercialOptional = comercialDAO.find(comercial.getId());
        Assertions.assertTrue(comercialOptional.isEmpty());
    }
}




