/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.mycompany.database.Connection;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jsantos
 */
public class LogsCpuLeitor {

    public void insertLogCpu(Integer fk_cpu) {

        CpuLeitor cpu = new CpuLeitor();
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

        String insert = "INSERT INTO logs_cpu (em_uso, data_hora, fk_cpu) VALUES ( ?,?,?);";

        template.update(insert,
                cpu.emUso(),
                LocalDateTime.now(),
                fk_cpu
        );
        templateLocal.update(insert,
                cpu.emUso(),
                LocalDateTime.now(),
                fk_cpu
        );

    }

}
