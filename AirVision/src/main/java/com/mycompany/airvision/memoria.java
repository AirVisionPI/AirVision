    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airvision;

/**
 *
 * @author jsantos
 */
public class memoria {
    
    private Integer idMemoria;
    private String total;
    private String disponivel;        
    private String uso;

    public Integer getIdMemoria() {
        return idMemoria;
    }

    public void setIdMemoria(Integer idMemoria) {
        this.idMemoria = idMemoria;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

  

    @Override
    public String toString() {
        return "ram{" + "idRam=" + idMemoria + ", total=" + total + ", disponivel=" + disponivel + ", uso=" + uso + '}';
    }
    
    
    
    
    
    
    
    
}
