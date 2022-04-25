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
    
    public void insertLogDisco(Integer fk_disco){
        
         Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        
        
        template.update("INSERT INTO logs_disco ( disco_leitura, disco_escrita, tamanho_atual_fila, data_hora, fk_disco) VALUES ( ?,?,?,?,?);",
                disco.getBytesDeLeitura(),
                disco.getBytesDeEscritas(),
                disco.getTamanhoAtualDaFila(),
                LocalDateTime.now(),
                fk_disco
        );
        
    }
    
    
}
