/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.mycompany.database.Connection;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mycompany.airvision.Disco;
import com.mycompany.airvision.Maquina;
import com.mycompany.airvision.memoria;
import com.mycompany.airvision.Cpu;
import java.io.IOException;
import com.mycompany.database.SlackConnection;
import java.security.spec.ECField;

/**
 *
 * @author jsantos
 */
public class MetodoInsert {

    private Connection config;
    private Integer fk_aeroporto;
    private JdbcTemplate template;
    private JdbcTemplate templateLocal;
    private maquinaLeitor maquina;
    private LogsCpuLeitor logsCpu;
    private LogsDiscoLeitor logsDisco;
    private LogsRamInsert logsRam;
    private List<Maquina> maquinas;
    private List<Maquina> maquinasLocal;
    private List<Disco> disco;
    private List<Disco> discoLocal;
    private List<memoria> ram;
    private List<memoria> ramLocal;
    private List<Cpu> cpu;
    private List<Cpu> cpuLocal;
    private CpuLeitor cpuLendo;
    private ramLeitor ramLendo;
    private DiscoLeitor discoLendo;

    public MetodoInsert(Integer fk_aeroporto) {
        this.config = new Connection();
        this.fk_aeroporto = fk_aeroporto;
        this.template = new JdbcTemplate(config.getDataSource());
        this.templateLocal = new JdbcTemplate(config.getDataSourceLocal());
        this.maquina = new maquinaLeitor();
        this.logsRam = new LogsRamInsert();
        this.logsCpu = new LogsCpuLeitor();
        this.logsDisco = new LogsDiscoLeitor();
        this.cpuLendo = new CpuLeitor();
        this.ramLendo = new ramLeitor();
        this.discoLendo = new DiscoLeitor();

    }

    public void slackNotify() throws IOException {
        Double cpuPorcentagemSlack = cpuLendo.emUso();
        if (cpuPorcentagemSlack > 74) {
            SlackConnection.sendMessageToSlack(
                    "Sua máquina está com consumo de CPU acima de 75%\n"
                    + "Esté foi o registro coletado: " + cpuPorcentagemSlack + "%\n"
                    + "nosso Script de correção será ativado automáticamente\n");
        }
        Double ramPorcentagemSlack = ramLendo.ramPorcentagemDeUso();
        if (ramPorcentagemSlack > 74) {
            SlackConnection.sendMessageToSlack(
                    "Sua máquina está com consumo de RAM acima de 75%\n"
                    + "Esté foi o registro coletado: " + ramPorcentagemSlack + "%\n"
                    + "nosso Script de correção será ativado automáticamente\n");
        }
        Double discoTimeResSlack = discoLendo.taxaDeTransferenciaDisco();
        if (discoTimeResSlack > 60) {
            SlackConnection.sendMessageToSlack(
                    "Sua máquina está com consumo de DISCO acima do normal!\n"
                    + "Esté foi o registro coletado: " + discoTimeResSlack + "Segundos\n"
                    + "nosso Script de correção será ativado automáticamente\n");
        }
    }

    private void getComponentes() throws IOException {
        
        slackNotify();
        String selectMaquina = "select * from maquina where fk_aeroporto = ? and hostname = ?";
        maquinas = template.query(selectMaquina, new BeanPropertyRowMapper(com.mycompany.airvision.Maquina.class), fk_aeroporto, maquina.getHostName());
        try {
            maquinasLocal = templateLocal.query(selectMaquina, new BeanPropertyRowMapper(com.mycompany.airvision.Maquina.class), fk_aeroporto, maquina.getHostName());
        } catch (Exception e) {
            System.out.println("DEU ERRO AO LISTAR MAQUINAS PARA DAR SELECT NOS COMPONENTES PARA INSERIR LOGS NO METODOINSERT");
            System.out.println(e.getMessage());
        }

        String selectDisco = "SELECT * FROM disco WHERE fk_maquina = ?";
        disco = template.query(selectDisco, new BeanPropertyRowMapper(com.mycompany.airvision.Disco.class), maquinas.get(0).getId_maquina());
        try {
            discoLocal = templateLocal.query(selectDisco, new BeanPropertyRowMapper(com.mycompany.airvision.Disco.class), maquinasLocal.get(0).getId_maquina());
        } catch (Exception e) {
            System.out.println("DEU ERRO NO SELECT LOGS DISCO NO METODOINSERT");
            System.out.println(e.getMessage());
        }

        String selectMemoria = "SELECT * FROM memoria WHERE fk_maquina = ?";
        ram = template.query(selectMemoria, new BeanPropertyRowMapper(com.mycompany.airvision.memoria.class), maquinas.get(0).getId_maquina());
        try {
            ramLocal = templateLocal.query(selectMemoria, new BeanPropertyRowMapper(com.mycompany.airvision.memoria.class), maquinasLocal.get(0).getId_maquina());
        } catch (Exception e) {
            System.out.println("DEU ERRO NO SELECT LOGS MEMORIA NO METODOINSERT");
            System.out.println(e.getMessage());
        }

        String selectCpu = "SELECT * FROM cpu WHERE fk_maquina = ?";
        cpu = template.query(selectCpu, new BeanPropertyRowMapper(com.mycompany.airvision.Cpu.class), maquinas.get(0).getId_maquina());
        try {
            cpuLocal = templateLocal.query(selectCpu, new BeanPropertyRowMapper(com.mycompany.airvision.Cpu.class), maquinasLocal.get(0).getId_maquina());
        } catch (Exception e) {
            System.out.println("DEU ERRO NO SELECT LOGS CPU NO METODOINSERT");
            System.out.println(e.getMessage());
        }
    }

    public void insertLogBanco() throws IOException {
        getComponentes();
        try {
            logsDisco.insertLogDisco(disco.get(0).getId_disco(), discoLocal.get(0).getId_disco());
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR DISCO NO METODOINSERT");
            System.out.println(e.getMessage());
        }
        try {
            logsRam.insertLogRam(ram.get(0).getIdMemoria(), ramLocal.get(0).getIdMemoria());
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR RAM NO METODOINSERT");
            System.out.println(e.getMessage());
        }
        try {
            logsCpu.insertLogCpu(cpu.get(0).getId_cpu(), cpuLocal.get(0).getId_cpu());
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR CPU NO METODOINSERT");
            System.out.println(e.getMessage());
        }
    }

    public void insertMaquina(Integer fk_maquina) {
        maquinaLeitor maquina = new maquinaLeitor();
        try {
            maquina.insertMaquina(fk_maquina);
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR MAQUINA NO METODOINSERT");
            System.out.println(e.getMessage());
        }
    }

    public void insertBanco(Integer fk_aeroporto) {

        DiscoLeitor disco = new DiscoLeitor();
        ramLeitor ram = new ramLeitor();
        CpuLeitor cpu = new CpuLeitor();

        try {
            disco.insertDiscoLeitor(0, fk_aeroporto);
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR DISCO NO METODOINSERT");
            System.out.println(e.getMessage());
        }
        try {
            cpu.insertCpu(fk_aeroporto);
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR CPU NO METODOINSERT");
            System.out.println(e.getMessage());
        }
        try {
            ram.insertRam(fk_aeroporto);
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR RAM NO METODOINSERT");
            System.out.println(e.getMessage());
        }

        System.out.println(cpu);
        System.out.println(ram);
        System.out.println(disco);

    }

}
