/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.mycompany.database.Connection;
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

        DiscoLeitor discoLendoInsert = new DiscoLeitor();

        // INSTANCIANDO CONNECTION, É ONDE TEM TODOS OS CAMPOS DE CONFIGURAÇÃO DA CONEXÃO COM OS BANCOS DE DADOS.
        Connection config = new Connection();

        // 🎲 SCRIPTS SQL 🎲
        String insert = "INSERT INTO logs_disco (tamanho_do_volume, volume_utilizado, volume_disponivel, fk_disco, time_res_seconds) VALUES (?,?,?,?,?);";

// SQL SERVER  ------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        // EFETUANDO O SCRIPT NO ObjetoSQL(Azure)...
        template.update(insert,
                discoLendoInsert.tamanhoTotalDoDisco(),
                discoLendoInsert.volumeUtilizadoDoDisco(),
                discoLendoInsert.volumeDisponivelDoDisco(),
                fk_disco,
                discoLendoInsert.taxaDeTransferenciaDisco()
        );

// SQL LOCAL  --------------------
        try {
            // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
            // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
            JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

            // EFETUANDO O SCRIPT NO ObjetoSQL(Local)...
            templateLocal.update(insert,
                    discoLendoInsert.tamanhoTotalDoDisco(),
                    discoLendoInsert.volumeUtilizadoDoDisco(),
                    discoLendoInsert.volumeDisponivelDoDisco(),
                    fk_disco_local,
                    discoLendoInsert.taxaDeTransferenciaDisco()
            );
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR LOGS DISCO EM LOGSDISCOLEITOR");
            System.out.println(e.getMessage());
        }

    }

}
