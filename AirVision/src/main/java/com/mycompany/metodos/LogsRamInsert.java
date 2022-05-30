/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.mycompany.database.Connection;
import java.security.spec.ECField;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jsantos
 */
public class LogsRamInsert {

    public void insertLogRam(Integer fk_ram, Integer fk_ram_local) {

        ramLeitor ram = new ramLeitor();

        // INSTANCIANDO CONNECTION, É ONDE TEM TODOS OS CAMPOS DE CONFIGURAÇÃO DA CONEXÃO COM OS BANCOS DE DADOS.
        Connection config = new Connection();

        // 🎲 SCRIPTS SQL 🎲
        String insert = "INSERT INTO logs_memoria (ram_disponivel, ram_uso, fk_memoria, ram_porcentagem) VALUES ( ?,?,?,?);";

// SQL SERVER  ------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        // EFETUANDO O SCRIPT NO ObjetoSQL(Azure)...
        template.update(insert,
                ram.disponivel(),
                ram.emUso(),
                fk_ram,
                ram.ramPorcentagemDeUso()
        );

// SQL LOCAL  --------------------
        try {
            // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
            // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
            JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

            // EFETUANDO O SCRIPT NO ObjetoSQL(Local)...
            templateLocal.update(insert,
                    ram.disponivel(),
                    ram.emUso(),
                    fk_ram_local,
                    ram.ramPorcentagemDeUso()
            );
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR LOGS MEMORIA EM LOGSRAMINSERT");
            System.out.println(e.getMessage());
        }

    }

}
