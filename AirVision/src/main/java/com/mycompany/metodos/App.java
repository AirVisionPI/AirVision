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

// ESSES IMPORTS PARA LOGIN
import java.util.Arrays;
import com.mycompany.database.Connection;
import com.mycompany.airvision.Usuario;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import telas.TelaPrincipal;

// ESSES IMPORTS PARA INSERIR
import com.mycompany.metodos.MetodoInsert;
import com.sun.java.accessibility.util.EventID;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import javax.swing.Timer;
import java.io.InputStreamReader;
import java.io.File;
import java.io.BufferedReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import oshi.SystemInfo;

/**
 *
 * @author jsantos
 */
public class App {

    public static void clear() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void sleep(Integer second, String message) {
        try {
            for (int i = second; i > 0; i--) {
                clear();
                if (message.length() >= 1) {
                    System.out.println("\n-----------------------> " + message);
                }
                System.out.println("\n======================= Aguarde: " + i + "... ========================");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }

    public static void sleepSecond(Integer second) {
        try {
            for (int i = second; i > 0; i--) {
                System.out.println("\n======================= Aguarde: " + i + "... ========================");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }

    public static Double converterByteToGigabyte(Double valorByte) {
        Double convertido1 = valorByte / 1024;
        Double convertido2 = convertido1 / 1024;
        Double convertido3 = convertido2 / 1024;

        return convertido3;
    }

    public static Double converterMillisecondsToSeconds(Double milliseconds) {
        Double seconds = (milliseconds / 1000) % 60;

        return seconds;
    }

    public static void painelDeControle(
            String nome,
            Integer quantidadeRegistrado,
            String processador,
            String fabricanteProcessador,
            String hostname,
            Double tamanhoDoVolume,
            Double volumeUtilizado,
            Double volumeDisponivel,
            Double tamanhoDaRam,
            Double ramUtilizado,
            Double ramDisponivel,
            Double cpu,
            Double ram,
            Double disco
    ) {
        // OBJETO PARA FORMATAR DOUBLE
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        formatador.setRoundingMode(RoundingMode.DOWN);

        System.out.println(String.format(""
                + "\n|=================================================================|"
                + "\n|--------------     PAINEL DE MONITORAMENTO     ------------------|"
                + "\n|=================================================================|"
                + "\n   Usuario: %s"
                + "\n"
                + "\n|-----------------------------------------------------------------|"
                + "\n|--------------        DADOS DA MAQUINA         ------------------|"
                + "\n   Hostname: %s"
                + "\n   Processador: %s"
                + "\n   Fabricante: %s"
                + "\n"
                + "\n   Tamanho do volume     | Utilizado         | Disponivel          "
                + "\n         %s GB             %s GB           %s GB                   "
                + "\n"
                + "\n   Tamanho da ram        | Utilizada         | Disponivel          "
                + "\n         %s GB             %s GB            %s GB                  "
                + "\n"
                + "\n|-----------------------------------------------------------------|"
                + "\n|--------------          MONITORAMENTO          ------------------|"
                + "\n                           CPU: %s%%"
                + "\n                           RAM: %s%%"
                + "\n          Tempo/resposta Disco: %s Segundos"
                + "\n"
                + "\n|--------------              OUTROS             ------------------|"
                + "\n"
                + "\n                  Quantidade de Registros: %d"
                + "\n                      Exit ===> CTRL + C"
                + "\n",
                nome,
                hostname,
                processador,
                fabricanteProcessador,
                formatador.format(tamanhoDoVolume),
                formatador.format(volumeUtilizado),
                formatador.format(volumeDisponivel),
                formatador.format(tamanhoDaRam),
                formatador.format(ramUtilizado),
                formatador.format(ramDisponivel),
                formatador.format(cpu),
                formatador.format(ram),
                formatador.format(disco),
                quantidadeRegistrado
        ));
    }

    public static void main(String[] args) {
        Connection config = new Connection();
        JdbcTemplate con = new JdbcTemplate(config.getDataSource());
        Scanner leitor = new Scanner(System.in);
        List<Usuario> cadastroAdvancedUse;
        if (Arrays.stream(args).anyMatch("CLI"::equals)) {
            do {
                clear();
                System.out.println("\n==============================================================");
                System.out.println("                 EFETUAR LOGIN AIRVISION                      ");
                System.out.println("                    Exit ===> CTRL + C                        ");
                System.out.println("--------------------------------------------------------------");
                System.out.println("                 Email | Digite seu Email:                    ");
                String email;
                email = leitor.nextLine();
                System.out.println("--------------------------------------------------------------");
                System.out.println("                 Senha | Digite sua Senha:                    ");
                String senha;
                senha = leitor.nextLine();;
                cadastroAdvancedUse = con.query("SELECT * FROM usuario "
                        + "WHERE email_usuario = ? and senha_usuario = ? ",
                        new BeanPropertyRowMapper(Usuario.class), email, senha);

                if (!cadastroAdvancedUse.isEmpty()) {
                    String nomeUser = cadastroAdvancedUse.get(0).getNome_usuario();
                    clear();

                    try {
                        System.out.println("\n|====  Seja Bem-Vindo " + nomeUser + "  ====|\n...");
                        System.out.println("\n|====  Iniciando...\n");

                        Integer fk_aeroporto = cadastroAdvancedUse.get(0).getFk_aeroporto();

                        MetodoInsert inserir = new MetodoInsert();
                        LogsCpuLeitor logsCpu = new LogsCpuLeitor();
                        LogsDiscoLeitor logsDisco = new LogsDiscoLeitor();
                        LogsRamInsert logsRam = new LogsRamInsert();
                        SystemInfo si = new SystemInfo();

                        // LOOCA
                        Looca looca = new Looca();

                        inserir.insertMaquina(fk_aeroporto);
                        inserir.insertBanco(fk_aeroporto);
                        System.out.println("\n|====  Fazendo alguns ajustes...\n");

                        String systemHostname = si.getOperatingSystem().getNetworkParams().getHostName();
                        Integer qtdRegistros = 1;

                        while (true) {
                            // ATRIBUTOS
                            String processador = looca.getProcessador().getNome();
                            String fabricanteProcessador = looca.getProcessador().getFabricante();
                            Double tamanhoDoVolume = converterByteToGigabyte((double) looca.getGrupoDeDiscos().getTamanhoTotal());
                            Double volumeDisponivel = converterByteToGigabyte((double) looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());
                            Double volumeUtilizado = tamanhoDoVolume - volumeDisponivel;
                            Double tamanhoDaRam = converterByteToGigabyte((double) looca.getMemoria().getTotal());
                            Double ramUtilizado = converterByteToGigabyte((double) looca.getMemoria().getEmUso());
                            Double ramDisponivel = tamanhoDaRam - ramUtilizado;
                            Double cpu = looca.getProcessador().getUso();
                            Double ram = ramUtilizado * 100 / tamanhoDaRam;
                            Double disco = converterMillisecondsToSeconds((double) looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia());

                            // METODO PARA INSERIR NO AZURE
                            inserir.insertLogBanco(fk_aeroporto);

                            // METODO LIMPAR TERMINAL
                            clear();

                            // CHAMANDO PAINEL DE CONTROLE
                            painelDeControle(nomeUser,
                                    qtdRegistros,
                                    processador,
                                    fabricanteProcessador,
                                    systemHostname,
                                    tamanhoDoVolume,
                                    volumeUtilizado,
                                    volumeDisponivel,
                                    tamanhoDaRam,
                                    ramUtilizado,
                                    ramDisponivel,
                                    cpu,
                                    ram,
                                    disco
                            );
                            qtdRegistros++;

                            // TEMPO DE ESPERA PARA INSERÇÃO
                            Thread.sleep(7000);
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else {
                    sleep(2, "Usuario ou Senha Invalidos");
                }
            } while (cadastroAdvancedUse.isEmpty());
        } else {
            try {
                new TelaLogin().setVisible(true);
                DiscoLeitor disco = new DiscoLeitor();
                ramLeitor ram = new ramLeitor();
                CpuLeitor cpu = new CpuLeitor();
            } catch (Exception e) {
                System.out.println(""
                        + "\n===========================ERROR==============================\n"
                        + e.getMessage()
                        + "\n\n--------------------------------------------------------------"
                        + "\n"
                        + "          Algum erro foi detectado na inicialização...\n"
                        + "      VAMOS DIRECIONAR O TERMINAL PARA A APLICAÇÃO EM CLI\n"
                        + "                   Iniciando AirVision CLI em...\n"
                        + "\n"
                        + "==============================================================\n"
                );
                sleepSecond(10);
                String[] argCli = {"CLI"};
                main(argCli);
            }
        }

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
