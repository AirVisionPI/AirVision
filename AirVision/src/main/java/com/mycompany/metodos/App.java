/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Volume;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import telas.TelaLogin;

/**
 *
 * @author jsantos
 */
public class App {
        public static void main(String[] args) {

        new TelaLogin().setVisible(true);
        DiscoLeitor disco = new DiscoLeitor();
        ramLeitor ram = new ramLeitor();
        CpuLeitor cpu = new CpuLeitor();
        
        
        
//            System.out.println(cpu.info());
        
//
//        int i = 5;
//        do {
//            cpu.insertCpu();
//            ram.insertRam();
//            disco.insertDiscoLeitor(0);
//            
//            System.out.println(cpu);
//            System.out.println(ram);
//            System.out.println(disco);
//        } while (i < 10);
           
           
//        template.execute("DROP TABLE IF EXISTS Disco");
        
        
        
//        String criacaoTabelaPokemon = "CREATE TABLE Disco ("
//                + "idDisco INT PRIMARY KEY AUTO_INCREMENT,"
//                + "fkMaquina Varchar(45)," 
//                + "nome Varchar(45),"
//                + "modelo Varchar(45)"
//                + "tamanho Varchar(45)"
//                + "qtdLeitura Varchar(45)"
//                + "bytesLeitura Varchar(45)"
//                + "qtdEscritas Varchar(45)"
//                + "tipo Varchar(45)"
//                + "montagem Varchar(45)"
//                + "dataDeRegistro datetime"
//                + ")";
//        
//        String insert = "INSERT INTO Disco VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        
//        String insertNaTabela = "INSERT INTO pokemon(nome) VALUES (" +
//                "'riolu'" +
//                ")";
//
//        Integer fkMaquina = 1;
//        template.execute(criacaoTabelaPokemon);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        DiscosGroup grupo = looca.getGrupoDeDiscos();
//        List<Disco> discos = grupo.getDiscos();
//        List<Volume> volumes = grupo.getVolumes();
//        
//        template.update(insert, null, );
//
//
//        List<Pokemon> listaPokemon1 = template.query("select * from pokemon", new BeanPropertyRowMapper<>(Pokemon.class));
//        
//        for (int i = 0; i < listaPokemon1.size(); i++) {
//            System.out.println(listaPokemon1.get(i).getNome());
//        }

//        for (Pokemon pokemon : listaPokemon1) {
//            System.out.println(pokemon.getNome());
//        }
//        
//        System.out.println(listaPokemon1);
//        
//          Conexao conexao = new Conexao();
//        JdbcTemplate con = new JdbcTemplate(conexao.getDataSource());
//        Disco disco = new Disco();
//        
//        con.execute("DROP TABLE IF EXISTS Logs");
//        StringBuilder create = new StringBuilder();
//        
//        create.append("CREATE TABLE Logs (");
//        create.append("idAluno int,");
//        create.append("idMaquina int,");
//        create.append("momento datetime default current_timestamp,");
//        create.append("temperatura float,");
//        create.append("consumoMemoria bigint(20),");
//        create.append("consumoCPU bigint(20),");
//        create.append("consumoDisco bigint(20),");
//        create.append("tempoEmAtividade Varchar(100),");
//        create.append("Inicializado MEDIUMTEXT");
//        create.append(");");
//        
//        con.update(create.toString());
//        
//        Logs logs = new Logs();
//        logs.capturarDadosDaMaquina();
    }
}
