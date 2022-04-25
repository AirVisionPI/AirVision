
package com.mycompany.airvision;

import java.util.Date;

/**
 *
 * @author jsantos
 */
public class LogsDisco {
    
    private Integer id_logs_disco;
    private String disco_leitura;
    private String disco_escrita;
    private String tamanho_atual_fila;
    private Date data_hora;
    private Integer fk_disco;
    
    // getters

    public Integer getId_logs_disco() {
        return id_logs_disco;
    }

    public String getDisco_leitura() {
        return disco_leitura;
    }

    public String getDisco_escrita() {
        return disco_escrita;
    }

    public String getTamanho_atual_fila() {
        return tamanho_atual_fila;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public Integer getFk_disco() {
        return fk_disco;
    }
    
    // setters

    public void setId_logs_disco(Integer id_logs_disco) {
        this.id_logs_disco = id_logs_disco;
    }

    public void setDisco_leitura(String disco_leitura) {
        this.disco_leitura = disco_leitura;
    }

    public void setDisco_escrita(String disco_escrita) {
        this.disco_escrita = disco_escrita;
    }

    public void setTamanho_atual_fila(String tamanho_atual_fila) {
        this.tamanho_atual_fila = tamanho_atual_fila;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public void setFk_disco(Integer fk_disco) {
        this.fk_disco = fk_disco;
    }

    @Override
    public String toString() {
        return "LogsDisco{" + "id_logs_disco=" + id_logs_disco + ", disco_leitura=" + disco_leitura + ", disco_escrita=" + disco_escrita + ", tamanho_atual_fila=" + tamanho_atual_fila + ", data_hora=" + data_hora + ", fk_disco=" + fk_disco + '}';
    }
    
    
    
}
