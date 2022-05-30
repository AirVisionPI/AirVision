/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.github.britooo.looca.api.core.Looca;
import telas.TelaLogin;

// ESSES IMPORTS PARA LOGIN
import java.util.Arrays;
import com.mycompany.database.Connection;
import com.mycompany.airvision.Usuario;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

// ESSES IMPORTS PARA INSERIR
import com.mycompany.metodos.MetodoInsert;
import com.mycompany.metodos.Utils;
import java.io.File;
import java.io.IOException;
import oshi.SystemInfo;

/**
 *
 * @author jsantos
 */
public class App {

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
                + "\n         %.2f GB             %.2f GB           %.2f GB                   "
                + "\n"
                + "\n   Tamanho da ram        | Utilizada         | Disponivel          "
                + "\n         %.2f GB             %.2f GB            %.2f GB                  "
                + "\n"
                + "\n|-----------------------------------------------------------------|"
                + "\n|--------------          MONITORAMENTO          ------------------|"
                + "\n                           CPU: %.2f%%"
                + "\n                           RAM: %.2f%%"
                + "\n          Tempo/resposta Disco: %.2f Segundos"
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
                tamanhoDoVolume,
                volumeUtilizado,
                volumeDisponivel,
                tamanhoDaRam,
                ramUtilizado,
                ramDisponivel,
                cpu,
                ram,
                disco,
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
                Utils.clear();
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
                    Utils.clear();

                    try {
                        System.out.println("\n|====  Seja Bem-Vindo " + nomeUser + "  ====|\n...");
                        System.out.println("\n|====  Iniciando...\n");

                        Integer fk_aeroporto = cadastroAdvancedUse.get(0).getFk_aeroporto();

                        MetodoInsert inserir = new MetodoInsert(fk_aeroporto);
                        SystemInfo si = new SystemInfo();
                        // LOOCA
                        CpuLeitor cpuLendo = new CpuLeitor();
                        DiscoLeitor discoLendo = new DiscoLeitor();
                        ramLeitor ramLendo = new ramLeitor();

                        inserir.insertMaquina(fk_aeroporto);
                        inserir.insertBanco(fk_aeroporto);
                        System.out.println("\n|====  Fazendo alguns ajustes...\n");

                        String systemHostname = si.getOperatingSystem().getNetworkParams().getHostName();
                        Integer qtdRegistros = 1;

                        while (true) {
                            // ATRIBUTOS

                            String processador = cpuLendo.nomeProcessador();
                            String fabricanteProcessador = cpuLendo.frabricante();
                            Double tamanhoDoVolume = discoLendo.tamanhoTotalDoDisco();
                            Double volumeDisponivel = discoLendo.volumeDisponivelDoDisco();
                            Double volumeUtilizado = discoLendo.volumeUtilizadoDoDisco();
                            Double tamanhoDaRam = ramLendo.total();
                            Double ramUtilizado = ramLendo.emUso();
                            Double ramDisponivel = ramLendo.disponivel();
                            Double cpu = cpuLendo.emUso();
                            Double ram = ramLendo.ramPorcentagemDeUso();
                            Double disco = discoLendo.taxaDeTransferenciaDisco();

                            // METODO PARA INSERIR NO AZURE
                            try {
                                // INSEERÇAO
                                inserir.insertLogBanco();
                            } catch (IOException ex) {
                                System.out.println("DEU ERRO NA INSERÇÃO DE LOGS APP(CLI) COM TRY CATCH");
                                ex.getMessage();
                            }

                            // METODO LIMPAR TERMINAL
                            Utils.clear();

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
                    Utils.sleep(2, "Usuario ou Senha Invalidos");
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
                Utils.sleepSecond(10);
                String[] argCli = {"CLI"};
                main(argCli);
            }
        }
        try {
            File LogRecordFile = new File("User.txt");
            if (LogRecordFile.createNewFile()) {
                System.out.println("File created: " + LogRecordFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
