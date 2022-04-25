package com.mycompany.airvision;

public class Usuario {
    
    private Integer id_usuario;
    private String nome_usuario;
    private String email_usuario;
    private String senha_usuario;
    private String cargo_usuario;
    private Integer fk_aeroporto;

    
    // getters
    public Integer getId_usuario() {
        return id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public String getCargo_usuario() {
        return cargo_usuario;
    }

    public Integer getFk_aeroporto() {
        return fk_aeroporto;
    }

    // setters

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public void setCargo_usuario(String cargo_usuario) {
        this.cargo_usuario = cargo_usuario;
    }

    public void setFk_aeroporto(Integer fk_aeroporto) {
        this.fk_aeroporto = fk_aeroporto;
    }
    
    
    
    
}
