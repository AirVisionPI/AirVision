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

    public void insertLogCpu(Integer fk_cpu, Integer fk_cpu_local) {

        CpuLeitor cpu = new CpuLeitor();

        // INSTANCIANDO CONNECTION, É ONDE TEM TODOS OS CAMPOS DE CONFIGURAÇÃO DA CONEXÃO COM OS BANCOS DE DADOS.
        Connection config = new Connection();

        // 🎲 SCRIPTS SQL 🎲
        String insert = "INSERT INTO logs_cpu (em_uso, fk_cpu) VALUES ( ?,?);";

// SQL SERVER  ------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        // EFETUANDO O SCRIPT NO ObjetoSQL(Azure)...
        template.update(insert,
                cpu.emUso(),

                fk_cpu
        );

// SQL LOCAL  --------------------
        try {
            // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
            // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
            JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

            // EFETUANDO O SCRIPT NO ObjetoSQL(Local)...
            templateLocal.update(insert,
                    cpu.emUso(),

                    fk_cpu_local
            );
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR LOGS CPU EM LOGSCPULEITOR");
            System.out.println(e.getMessage());
        }

    }

}
