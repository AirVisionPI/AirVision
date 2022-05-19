/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Disco;
import com.mycompany.airvision.Maquina;
import com.mycompany.database.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jsantos
 */
public class LogsDiscoLeitor {

    private Looca looca;
    private Disco disco;

    public LogsDiscoLeitor() {
        this.looca = new Looca();
        this.disco = looca.getGrupoDeDiscos().getDiscos().get(0);
    }

    public void insertLogDisco(Integer fk_disco, Integer fk_disco_local) {

        // INSTANCIANDO CONNECTION, É ONDE TEM TODOS OS CAMPOS DE CONFIGURAÇÃO DA CONEXÃO COM OS BANCOS DE DADOS.
        Connection config = new Connection();

        // 🎲 SCRIPTS SQL 🎲
        String insert = "INSERT INTO logs_disco ( disco_leitura, disco_escrita, tamanho_atual_fila, data_hora, fk_disco) VALUES ( ?,?,?,?,?);";

// SQL SERVER  ------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        // EFETUANDO O SCRIPT NO ObjetoSQL(Azure)...
        template.update(insert,
                disco.getBytesDeLeitura(),
                disco.getBytesDeEscritas(),
                disco.getTamanhoAtualDaFila(),
                LocalDateTime.now(),
                fk_disco
        );

// SQL LOCAL  --------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

        // EFETUANDO O SCRIPT NO ObjetoSQL(Local)...
        templateLocal.update(insert,
                disco.getBytesDeLeitura(),
                disco.getBytesDeEscritas(),
                disco.getTamanhoAtualDaFila(),
                LocalDateTime.now(),
                fk_disco_local
        );

    }

}
