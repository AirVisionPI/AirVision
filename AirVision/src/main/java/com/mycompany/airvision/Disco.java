/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airvision;

/**
 *
 * @author jsantos
 */
public class Disco {
    //atributos
    private Integer id_disco;
    private String nome;
    private String modelo;
    private String fk_maquina;
    
    // getters

    public Integer getId_disco() {
        return id_disco;
    }

    public String getNome() {
        return nome;
    }

    public String getModelo() {
        return modelo;
    }

    public String getFk_maquina() {
        return fk_maquina;
    }
    
    // setters

    public void setId_disco(Integer id_disco) {
        this.id_disco = id_disco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setFk_maquina(String fk_maquina) {
        this.fk_maquina = fk_maquina;
    }

    //ToString

    @Override
    public String toString() {
        return "Disco{" + "id_disco=" + id_disco + ", nome=" + nome + ", modelo=" + modelo + ", fk_maquina=" + fk_maquina + '}';
    }
    
    
}
