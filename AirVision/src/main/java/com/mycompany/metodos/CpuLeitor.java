/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.mycompany.database.Connection;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mycompany.airvision.Cpu;
import com.mycompany.airvision.Maquina;
import com.mycompany.database.SlackConnection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author jsantos
 */
public class CpuLeitor {

//    id_logs_cpu INT PRIMARY KEY AUTO_INCREMENT,
//    em_uso VARCHAR(3),
//    data_hora DATETIME,
//    fk_cpu INT,
    private Looca looca;
    private Processador cpu;
    private Maquina maquina;

    public CpuLeitor() {
        this.looca = new Looca();
        this.cpu = looca.getProcessador();
    }

    public Double emUso() {
        BigDecimal ramPorcentagem = new BigDecimal(cpu.getUso()).setScale(2, RoundingMode.UP);
        return ramPorcentagem.doubleValue();
    }

    public Processador info() {
        return cpu;
    }

    public String identificador() {
        return cpu.getIdentificador();
    }

    public String nomeProcessador() {
        return cpu.getNome();
    }

    public String frabricante() {
        return cpu.getFabricante();
    }

    public void insertCpu(Integer fk_aeroporto) {
        maquinaLeitor maquinaleitor = new maquinaLeitor();

        // INSTANCIANDO CONNECTION, É ONDE TEM TODOS OS CAMPOS DE CONFIGURAÇÃO DA CONEXÃO COM OS BANCOS DE DADOS.
        Connection config = new Connection();

        // 🎲 SCRIPTS SQL 🎲
        String insert = "INSERT INTO cpu ( nome_processador, identificador, fabricante, fk_maquina ) VALUES (?,?,?,?);";
        String select = "SELECT * from maquina where hostname = ? and fk_aeroporto = ?";

// SQL SERVER  ------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        // INSTANCIANDO LISTA E SEU CONTEÚDO=SELECT DO BANCO AZURE
        // EFETUANDO O SCRIPT SELECT NO Template(ObjetoSQL Azure), isto está em Connection...
        List<Maquina> maquinas = template.query(select, new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName(), fk_aeroporto);

        // EFETUANDO O SCRIPT NO ObjetoSQL(Azure)...
        template.update(insert,
                cpu.getNome(),
                cpu.getIdentificador(),
                cpu.getFabricante(),
                maquinas.get(0).getId_maquina()
        );

// SQL LOCAL  --------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

        // INSTANCIANDO LISTA E SEU CONTEÚDO=SELECT DO BANCO LOCAL
        // EFETUANDO O SCRIPT SELECT NO TemplateLocal(ObjetoSQL Local), isto está em Connection...
        List<Maquina> maquinasLocal = templateLocal.query(select, new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName(), fk_aeroporto);

        // EFETUANDO O SCRIPT NO ObjetoSQL(Local)...
        templateLocal.update(insert,
                cpu.getNome(),
                cpu.getIdentificador(),
                cpu.getFabricante(),
                maquinasLocal.get(0).getId_maquina()
        );
        
//                if (emUso() > 75) {
//                    SlackConnection.sendMessageToSlack("Sr.(a) " + usuario.getNome()
//                    + ", sua máquina está com consumo de CPU acima de 75%"
//                    + ", nosso Script de correção será ativado automáticamente");
//
//    }
    }

    public List<Cpu> selectCpu() {

        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        String select = "select * from cpu";

        return template.query(select, new BeanPropertyRowMapper(Cpu.class));
    }

}
