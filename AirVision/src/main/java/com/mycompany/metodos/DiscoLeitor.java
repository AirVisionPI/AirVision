/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.mycompany.database.Connection;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Disco;
import com.mycompany.airvision.Maquina;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;



/**
 *
 * @author jsantos
 */
public class DiscoLeitor {

    private Looca looca;
    private DiscosGroup disco;
    private Maquina maquina;
    
    public DiscoLeitor() {
        this.looca = new Looca();
        this.disco = looca.getGrupoDeDiscos();
    }

    public List<Disco> listaDiscos(){
        return disco.getDiscos();
    }

    public Integer qtdDiscos(){
        return disco.getQuantidadeDeDiscos();
    }

    public Integer qtdVolume(){
        return disco.getQuantidadeDeVolumes();
    }

    public Long tamanhoTotal(){
       return disco.getTamanhoTotal();
    }

    public Disco getDisco(Integer index){
        return disco.getDiscos().get(index);
    }

    public void insertDiscoLeitor(Integer index){
        maquinaLeitor maquinaleitor = new maquinaLeitor();
        
        Disco disco = getDisco(index);
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        
        List<Maquina> maquinas = template.query("SELECT * from maquina where hostname = ?", new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName());
        
        for (Maquina maquina1 : maquinas) {
            System.out.println("maquinas: " + maquina1);
        }
        
        template.update("INSERT INTO disco ( nome, modelo, fk_maquina) VALUES ( ?,?,?);",
                disco.getNome(),
                disco.getModelo(),
                maquinas.get(0).getId_maquina()
                
//                disco.getBytesDeLeitura(),
//                disco.getBytesDeEscritas(),
//                disco.getTamanhoAtualDaFila(),
//                disco.getTempoDeTransferencia()
        );


    }
    
    public List<com.mycompany.airvision.Disco> selectDiscos(){
        
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        String select = "select * from Disco";

        return template.query(select,new BeanPropertyRowMapper(com.mycompany.airvision.Disco.class));

    }
}
