
package com.mycompany.airvision;

import java.util.Date;

/**
 *
 * @author jsantos
 */
public class LogsCpu {
    
    private Integer id_logs_cpu;
    private String em_uso;
    private Date data_hora;
    private Integer fk_cpu;

    // getters
    
    public Integer getId_logs_cpu() {
        return id_logs_cpu;
    }

    public String getEm_uso() {
        return em_uso;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public Integer getFk_cpu() {
        return fk_cpu;
    }
    
    // setters

    public void setId_logs_cpu(Integer id_logs_cpu) {
        this.id_logs_cpu = id_logs_cpu;
    }

    public void setEm_uso(String em_uso) {
        this.em_uso = em_uso;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public void setFk_cpu(Integer fk_cpu) {
        this.fk_cpu = fk_cpu;
    }
    
    // toString

    @Override
    public String toString() {
        return "LogsCpu{" + "id_logs_cpu=" + id_logs_cpu + ", em_uso=" + em_uso + ", data_hora=" + data_hora + ", fk_cpu=" + fk_cpu + '}';
    }
    
    
    
}
