package com.example.demo_sp_th_2025.DAO;

import com.example.demo_sp_th_2025.modelo.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring
//que forma parte de la 'capa de persistencia'.
@Repository

public class ClienteDAOImpl implements ClienteDAO {

    //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void create(Cliente cliente) {

        //Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
        String sql = """
							INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoria) 
							VALUES  (     ?,         ?,         ?,       ?,         ?)
						   """;

        String[] ids = {"id"};

        //Con recuperación de id generado
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, ids);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setString(4, cliente.getCiudad());
            ps.setInt(5, cliente.getCategoria());

            return ps;

        },keyHolder);

        cliente.setId((int) keyHolder.getKey());

    }

    @Override
    public List<Cliente> getAll() {

        List<Cliente> listFab = jdbcTemplate.query("""
                        SELECT * FROM cliente """,
                (rs, rowNum) -> new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoria")
                )
        );

        log.info("Devueltos {} registros.", listFab.size());

        return listFab;

    }

    @Override
    public Optional<Cliente> find(int id) {

    try {
        Cliente cliente = jdbcTemplate.queryForObject("""
                
                        select * from cliente 
                         where id = ?
                """,
                (ResultSet rs, int rowNum) -> Cliente.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombte"))
                        .apellido1(rs.getString("apellido1"))
                        .apellido2(rs.getString("apellido2"))
                        .ciudad(rs.getString("ciudad"))
                        .categoria(rs.getInt("categoria"))
                        .build()
                ,
                id
                );
        return Optional.of(cliente);

    } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
    }
}

    @Override
    public void update(Cliente cliente) {
        int rows = jdbcTemplate.update("""
										UPDATE cliente SET 
														nombre = ?, 
														apellido1 = ?, 
														apellido2 = ?,
														ciudad = ?,
														categoria = ?  
												WHERE id = ?
										""", cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria()
                , cliente.getId());

        log.info("Update de Cliente con {} registros actualizados.", rows);

    }


    @Override
    public void delete(int id) {

        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        log.info("Delete de Cliente con {} registros eliminados.", rows);

    }

}
