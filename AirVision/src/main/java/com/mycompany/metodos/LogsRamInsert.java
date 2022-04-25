/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.github.britooo.looca.api.core.Looca;
import com.mycompany.database.Connection;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jsantos
 */
public class LogsRamInsert {
        
    
        public void insertLogRam(Integer fk_ram){
            
            ramLeitor ram = new ramLeitor();  
         Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        
        
        template.update("INSERT INTO logs_memoria (ram_disponivel, ram_uso, data_hora, fk_memoria) VALUES ( ?,?,?,?);",
                ram.disponivel(),
                ram.emUso(),
                LocalDateTime.now(),
                fk_ram
        );  
        
    }
    
    
}
