/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airvision;

/**
 *
 * @author jsantos
 */
public class Maquina {
    
    
    //atributos
    
    private Integer id_maquina;
    private String hostname;
    private String sistema_operacional;
    private Integer fk_aeroporto;

    
    // constructor

    public Maquina(Integer id_maquina, String hostname, String sistema_operacional, Integer fk_aeroporto) {
        this.id_maquina = id_maquina;
        this.hostname = hostname;
        this.sistema_operacional = sistema_operacional;
        this.fk_aeroporto = fk_aeroporto;
    }

    public Maquina() {
    }
    
    
    
    // getters

    public Integer getId_maquina() {
        return id_maquina;
    }

    public String getHostname() {
        return hostname;
    }

    public String getSistema_operacional() {
        return sistema_operacional;
    }

    public Integer getFk_aeroporto() {
        return fk_aeroporto;
    }
    
    
    
    // setters

    public void setId_maquina(Integer id_maquina) {
        this.id_maquina = id_maquina;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setSistema_operacional(String sistema_operacional) {
        this.sistema_operacional = sistema_operacional;
    }

    public void setFk_aeroporto(Integer fk_aeroporto) {
        this.fk_aeroporto = fk_aeroporto;
    }

    @Override
    public String toString() {
        return "Maquina{" + "id_maquina=" + id_maquina + ", hostname=" + hostname + ", sistema_operacional=" + sistema_operacional + ", fk_aeroporto=" + fk_aeroporto + '}';
    }

    
    
    
    
    
}
