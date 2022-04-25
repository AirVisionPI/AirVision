
package com.mycompany.airvision;

import java.util.Date;

/**
 *
 * @author jsantos
 */
public class LogsRam {

    private Integer id_logs_memoria;
    private String ram_disponivel;
    private String ram_uso;
    private Date data_hora;
    private Integer fk_memoria;
    
    // getters

    public Integer getId_logs_memoria() {
        return id_logs_memoria;
    }

    public String getRam_disponivel() {
        return ram_disponivel;
    }

    public String getRam_uso() {
        return ram_uso;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public Integer getFk_memoria() {
        return fk_memoria;
    }
    
    // setters

    public void setId_logs_memoria(Integer id_logs_memoria) {
        this.id_logs_memoria = id_logs_memoria;
    }

    public void setRam_disponivel(String ram_disponivel) {
        this.ram_disponivel = ram_disponivel;
    }

    public void setRam_uso(String ram_uso) {
        this.ram_uso = ram_uso;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public void setFk_memoria(Integer fk_memoria) {
        this.fk_memoria = fk_memoria;
    }
    
    // toString

    @Override
    public String toString() {
        return "LogsRam{" + "id_logs_memoria=" + id_logs_memoria + ", ram_disponivel=" + ram_disponivel + ", ram_uso=" + ram_uso + ", data_hora=" + data_hora + ", fk_memoria=" + fk_memoria + '}';
    }
    
    
    
}
