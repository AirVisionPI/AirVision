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
        return cpu.getUso();
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
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

        String insert = "INSERT INTO cpu ( nome_processador, identificador,fabricante, fk_maquina ) VALUES (?,?,?,?);";

        List<Maquina> maquinas = template.query("SELECT * from maquina where hostname = ? and fk_aeroporto = ?", new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName(), fk_aeroporto);

        template.update(insert,
                cpu.getNome(),
                cpu.getIdentificador(),
                cpu.getFabricante(),
                maquinas.get(0).getId_maquina()
        );

        templateLocal.update(insert,
                cpu.getNome(),
                cpu.getIdentificador(),
                cpu.getFabricante(),
                maquinas.get(0).getId_maquina()
        );
    }

    public List<Cpu> selectCpu() {

        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        String select = "select * from cpu";

        return template.query(select, new BeanPropertyRowMapper(Cpu.class));
    }

}
