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


/**
 *
 * @author jsantos
 */
public class MetodoInsert {
    
    public void insertLogBanco(Integer fk_aeroporto){
        
            maquinaLeitor maquina = new maquinaLeitor();
          Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        String select = "select * from maquina where fk_aeroporto = ? and hostname = ?";
        List<Maquina> maquinas = template.query(select,new BeanPropertyRowMapper(com.mycompany.airvision.Maquina.class), fk_aeroporto, maquina.getHostName() );   
        
        LogsDiscoLeitor logsDisco = new LogsDiscoLeitor();   
        select = "select * from Disco where fk_maquina = ?";
        List<Disco> disco = template.query(select,new BeanPropertyRowMapper(com.mycompany.airvision.Disco.class), maquinas.get(0).getId_maquina());   
        logsDisco.insertLogDisco(disco.get(0).getId_disco());
        
        
        LogsRamInsert logsRam = new LogsRamInsert();
        select = "select * from memoria where fk_maquina = ?";
        List<memoria> ram = template.query(select,new BeanPropertyRowMapper(com.mycompany.airvision.memoria.class), maquinas.get(0).getId_maquina());
        logsRam.insertLogRam(ram.get(0).getIdMemoria());
        
        LogsCpuLeitor logsCpu = new LogsCpuLeitor();
        select = "select * from cpu where fk_maquina = ?";
        List<Cpu> cpu = template.query(select,new BeanPropertyRowMapper(com.mycompany.airvision.Cpu.class), maquinas.get(0).getId_maquina());        
        logsCpu.insertLogCpu(cpu.get(0).getId_cpu());
    }
    
    public void insertMaquina(Integer fk_maquina){
        maquinaLeitor maquina = new maquinaLeitor();
        maquina.insertMaquina(fk_maquina);
        
    }
    
    public void insertBanco(){
    
        DiscoLeitor disco = new DiscoLeitor();
        ramLeitor ram = new ramLeitor();
        CpuLeitor cpu = new CpuLeitor();


        disco.insertDiscoLeitor(0);
        
        cpu.insertCpu();
        ram.insertRam(Integer.MIN_VALUE);

        System.out.println(cpu);
        System.out.println(ram);
        System.out.println(disco);

    }
    
}