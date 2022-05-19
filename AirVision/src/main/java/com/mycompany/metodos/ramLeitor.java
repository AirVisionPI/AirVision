/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.mycompany.database.Connection;
import com.github.britooo.looca.api.core.Looca;
import com.mycompany.airvision.Maquina;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mycompany.airvision.memoria;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author jsantos
 */
public class ramLeitor {

    private Looca looca;

    public ramLeitor() {
        this.looca = new Looca();
    }

    public Long total() {
        return looca.getMemoria().getTotal();
    }

    public Long disponivel() {
        return looca.getMemoria().getDisponivel();
    }

    public Long emUso() {
        return looca.getMemoria().getEmUso();
    }

    public void insertRam(Integer fk_aeroporto) {
        maquinaLeitor maquinaleitor = new maquinaLeitor();
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());
        String insert = "INSERT INTO memoria ( total, fk_maquina) VALUES ( ?,?);";

        List<Maquina> maquinas = template.query("SELECT * from maquina where hostname = ? and fk_aeroporto = ?", new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName(), fk_aeroporto);

        template.update(insert,
                total(),
                maquinas.get(0).getId_maquina()
        );

        templateLocal.update(insert,
                total(),
                maquinas.get(0).getId_maquina()
        );
    }

    public List<memoria> selectRam() {
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        String select = "Select * from Ram;";

        return template.query(select, new BeanPropertyRowMapper(memoria.class));

    }

}
