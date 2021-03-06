/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.List;
/**
 *
 * @author jpiza
 */
public class ComponentesServices {
    Memoria ram = new Memoria();
    Looca looca = new Looca();
    Processador processador = new Processador();
    DiscosGroup discos = new DiscosGroup();

//    =======================================================================================================
//    RAM
    public Double tamanhoTotalRam() {
        return ram.getTotal().doubleValue();
    }

    public Double tamanhoUsadoRam() {
        return ram.getEmUso().doubleValue();
    }

    public Integer tamanhoDisponivelRam() {
        return ram.getDisponivel().intValue();
    }

    public Double getMemoriaEmUsoPorc() {
        return (tamanhoUsadoRam() * 100) / tamanhoTotalRam();
    }

//    ======================================================================================================
//
//    ======================================================================================================
//    Processador
    public void hostName() {
        try {
            String nomecomputador = InetAddress.getLocalHost().getHostName();
            System.out.println(nomecomputador);
        } catch (UnknownHostException e) {
            System.out.println("Exception caught =" + e.getMessage());
        }

    }

    public String nomeProcessador() {
        return processador.getNome();
    }

    public Integer usoProcessador() {
        return processador.getUso().intValue();
    }

    public String identificadorProcessador() {
        return processador.getIdentificador();
    }

    public Integer frequenciaProcessador() {
        return processador.getFrequencia().intValue();
    }

    public Double getCpuUsoPorc() {
        Integer cpuUso = usoProcessador();
        return cpuUso.doubleValue();
    }

//    ========================================================================================================
//    ========================================================================================================
//    Disco
    public Long tamanhoTotalDisco() {
        return discos.getTamanhoTotal() / 1000000000;
    }

    public List<Disco> totalDiscos() {
        return discos.getDiscos();
    }

    public Integer quantidadeDeDiscos() {
        return discos.getQuantidadeDeDiscos();
    }

    public List<Volume> totalVolume() {
        return discos.getVolumes();
    }

    public Integer quantidadeDeVolumes() {
        return discos.getQuantidadeDeVolumes();
    }

    public long getDiscoPorc() {

        List<Disco> discosConvertidos = totalDiscos();

        for (Disco disco : discosConvertidos) {
            System.out.println(disco);
            System.out.println(disco.getBytesDeEscritas());
        }

        return discos.getTamanhoTotal();
//                / (tamanhoTotalDisco() * 0.01);
//        return disco.get  (tamanhoTotalDisco() * 0.01) + 1000000000;

//        tamanhoUsadoRam() / (tamanhoTotalRam() * 0.0
    }

//    =========================================================================================================
}


