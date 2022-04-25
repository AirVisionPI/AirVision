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
    
    
    public String getHostName(){
        return this.si.getOperatingSystem().getNetworkParams().getHostName();
    }
        
    public String getSistema(){
        return sistema.getSistemaOperacional();
    }
    
        public void insertMaquina(Integer fk_aeroporto){
        
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        
        
        String select = "select * from Maquina where fk_aeroporto = ? and hostname = ?;";
        List<com.mycompany.airvision.Maquina> maquinas;
        maquinas = template.query(select,new BeanPropertyRowMapper(com.mycompany.airvision.Maquina.class), fk_aeroporto, getHostName());
        
        if(maquinas.isEmpty()){
            template.update("INSERT INTO maquina VALUES ( ?,?,?);",
                    getHostName(),
                    getSistema(),
                    fk_aeroporto
            );        
        }
    }
    
    public List<Maquina> selectMaquina(){
        
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        String select = "select * from Maquina";

        return template.query(select,new BeanPropertyRowMapper(com.mycompany.airvision.Maquina.class));

    }        
        
}
