/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airvision;

/**
 *
 * @author jsantos
 */
public class Cpu {
    
//Fabricante: GenuineIntel
//Nome: Intel(R) Core(TM) i5-8265U CPU @ 1.60GHz
//Id: BFEBFBFF000806EC
//Identificador: Intel64 Family 6 Model 142 Stepping 12
//Microarquitetura: Whiskey Lake
//Frequência: 1800000000
//Número de Pacotes Físicos: 1
//Número de CPUs Fisícas: 4
//Número de CPUs Lógicas: 8
    

    private Integer id_cpu;
    private String nome_processador;
    private String identificador;
    private String fabricante;
    private Integer fk_maquina;

 
    //getters

    public Integer getId_cpu() {
        return id_cpu;
    }

    public String getNome_processador() {
        return nome_processador;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getFabricante() {
        return fabricante;
    }

    public Integer getFk_maquina() {
        return fk_maquina;
    }
    
    //setters 

    public void setId_cpu(Integer id_cpu) {
        this.id_cpu = id_cpu;
    }

    public void setNome_processador(String nome_processador) {
        this.nome_processador = nome_processador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setFk_maquina(Integer fk_maquina) {
        this.fk_maquina = fk_maquina;
    }
    
    
    // to string

    @Override
    public String toString() {
        return "Cpu{" + "id_cpu=" + id_cpu + ", nome_processador=" + nome_processador + ", identificador=" + identificador + ", fabricante=" + fabricante + ", fk_maquina=" + fk_maquina + '}';
    }
    
    
    
    
}
