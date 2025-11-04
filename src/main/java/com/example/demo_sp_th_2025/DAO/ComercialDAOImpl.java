package com.example.demo_sp_th_2025.DAO;

import ch.qos.logback.classic.Logger;
import com.example.demo_sp_th_2025.modelo.Cliente;
import com.example.demo_sp_th_2025.modelo.Comercial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring
//que forma parte de la 'capa de persistencia'.
@Repository

public class ComercialDAOImpl implements ComercialDAO{
    //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial comercial) {
        //Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
        String sql = """
							INSERT INTO comercial (nombre, apellido1, apellido2, comision) 
							VALUES  (     ?,         ?,         ?,       ?)
						   """;

        String[] ids = {"id"};

        //Con recuperación de id generado
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, ids);

            ps.setString(1, comercial.getNombre());
            ps.setString(2, comercial.getApellido1());
            ps.setString(3, comercial.getApellido2());
            ps.setBigDecimal(4, comercial.getComision());

            return ps;

        },keyHolder);

        comercial.setId(keyHolder.getKey().intValue());

    }

    @Override
    public List<Comercial> getAll() {
        List<Comercial> listFab = jdbcTemplate.query("""
                        SELECT * FROM comercial """,
                (rs, rowNum) -> new Comercial(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getBigDecimal("comision")
                )
        );

        log.info("Devueltos {} registros.", listFab.size());

        return listFab;    }

    @Override
    public Optional<Comercial> find(int id) {
        try {
            Comercial comercial = jdbcTemplate.queryForObject("""
                
                        select * from comercial 
                         where id = ?
                """,
                    (ResultSet rs, int rowNum) -> Comercial.builder()
                            .id(rs.getInt("id"))
                            .nombre(rs.getString("nombre"))
                            .apellido1(rs.getString("apellido1"))
                            .apellido2(rs.getString("apellido2"))
                            .comision(rs.getBigDecimal("comision"))
                            .build()
                    ,
                    id
            );
            return Optional.of(comercial);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }    }

    @Override
    public void update(Comercial comercial) {
        int rowsUpdated = jdbcTemplate.update("""
										UPDATE comercial SET 
													    nombre = ?, 
														apellido1 = ?, 
														apellido2 = ?,
														comision = ?  
												WHERE id = ?
										""", comercial.getNombre()
                , comercial.getApellido1()
                , comercial.getApellido2()
                , comercial.getComision()
                , comercial.getId());

        log.debug("Update de Comercial con {} registros actualizados.", rowsUpdated);

    }

    @Override
    public void delete(int id) {
        int rowsDelete = jdbcTemplate.update("""
										DELETE FROM comercial   
							
												WHERE id = ?
										""", id);


        log.debug("Update de Comecial con {} registros actualizados.", rowsDelete);
        log.debug("Update de Comercial con {} registros actualizados.", rowsDelete);

    }

}

