/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.mycompany.database.Connection;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import com.mycompany.airvision.Maquina;

/**
 *
 * @author jsantos
 */
public class maquinaLeitor {

    Looca looca = new Looca();
    Sistema sistema = new Sistema();
    private final SystemInfo si = new SystemInfo();

//        public Long tamanhoTotal(){
//       return disco.getTamanhoTotal();
//    }
    public String getHostName() {
        return this.si.getOperatingSystem().getNetworkParams().getHostName();
    }

    public String getSistema() {
        return sistema.getSistemaOperacional();
    }

    public void insertMaquina(Integer fk_aeroporto) {

        // INSTANCIANDO CONNECTION, É ONDE TEM TODOS OS CAMPOS DE CONFIGURAÇÃO DA CONEXÃO COM OS BANCOS DE DADOS.
        Connection config = new Connection();

        // 🎲 SCRIPTS SQL 🎲
        String insert = "INSERT INTO maquina VALUES (?,?,?);";
        String select = "select * from maquina where fk_aeroporto = ? and hostname = ?;";

// SQL SERVER  ------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        // INSTANCIANDO LISTA E SEU CONTEÚDO=SELECT DO BANCO AZURE
        // EFETUANDO O SCRIPT SELECT NO Template(ObjetoSQL Azure), isto está em Connection...
        List<com.mycompany.airvision.Maquina> maquinas;
        maquinas = template.query(select, new BeanPropertyRowMapper(com.mycompany.airvision.Maquina.class), fk_aeroporto, getHostName());

        // EFETUANDO O SCRIPT NO ObjetoSQL(Azure)...
        if (maquinas.isEmpty()) {
            template.update(insert,
                    getHostName(),
                    getSistema(),
                    fk_aeroporto
            );
        }

// SQL LOCAL  --------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        try {
            JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

            // INSTANCIANDO LISTA E SEU CONTEÚDO=SELECT DO BANCO LOCAL
            // EFETUANDO O SCRIPT SELECT NO TemplateLocal(ObjetoSQL Local), isto está em Connection...
            List<com.mycompany.airvision.Maquina> maquinasLocal;
            maquinasLocal = templateLocal.query(select, new BeanPropertyRowMapper(com.mycompany.airvision.Maquina.class), fk_aeroporto, getHostName());;;;

            // EFETUANDO O SCRIPT NO ObjetoSQL(Local)...
            if (maquinasLocal.isEmpty()) {
                templateLocal.update("INSERT INTO maquina ( hostname, sistema_operacional, fk_aeroporto) VALUES (?,?,?);",
                        getHostName(),
                        getSistema(),
                        fk_aeroporto
                );
            }
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR MAQUINA EM MAQUINALEITOR");
            System.out.println(e.getMessage());
        }

    }

    public List<Maquina> selectMaquina() {
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        String select = "select * from Maquina";
        return template.query(select, new BeanPropertyRowMapper(com.mycompany.airvision.Maquina.class));
    }
}
