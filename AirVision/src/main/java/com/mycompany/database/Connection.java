/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author jsantos
 */
public class Connection {

    private BasicDataSource dataSource;
    private BasicDataSource dataSourceLocal;

    public Connection() {

        // CONFIGURAÇÕES BANCO DE DADOS AZURE
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://srv-airvision.database.windows.net:1433;database=bd-airvision2;user=admin-airvision@srv-airvision;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        dataSource.setUsername("admin-airvision");
        dataSource.setPassword("2ads@grupo3");

        // CONFIGURAÇÕES BANCO DE DADOS LOCAL
        dataSourceLocal = new BasicDataSource();
        dataSourceLocal.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceLocal.setUrl("jdbc:mysql://localhost:3308/airvision");
        dataSourceLocal.setUsername("root");
        dataSourceLocal.setPassword("root");

    }

    // RETORNANDO CONFIGURAÇÕES BANCO DE DADOS AZURE COM O MÉTODO GET
    public BasicDataSource getDataSource() {
        return dataSource;
    }

    // RETORNANDO CONFIGURAÇÕES BANCO DE DADOS LOCAL COM O MÉTODO GET
    public BasicDataSource getDataSourceLocal() {
        return dataSourceLocal;
    }
}
