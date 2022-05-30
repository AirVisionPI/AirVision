/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.mycompany.database.Connection;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Disco;
import com.mycompany.airvision.Maquina;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jsantos
 */
public class DiscoLeitor {

    private Looca looca;
    private DiscosGroup disco;

    public DiscoLeitor() {
        this.looca = new Looca();
        this.disco = looca.getGrupoDeDiscos();
    }

    public List<Disco> listaDiscos() {
        return disco.getDiscos();
    }

    public Integer qtdDiscos() {
        return disco.getQuantidadeDeDiscos();
    }

    public Integer qtdVolume() {
        return disco.getQuantidadeDeVolumes();
    }

    public Long tamanhoTotal() {
        return disco.getTamanhoTotal();
    }

    public Double tamanhoTotalDoDisco() {
        Double volumeConvert = Utils.converterByteToGigabyte((double) looca.getGrupoDeDiscos().getTamanhoTotal());
        BigDecimal tamanhoDoVolume = new BigDecimal(volumeConvert).setScale(2, RoundingMode.UP);
        return tamanhoDoVolume.doubleValue();
    }

    public Double volumeDisponivelDoDisco() {
        Double volumeConvert = Utils.converterByteToGigabyte((double) looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());
        BigDecimal volumeDisponivel = new BigDecimal(volumeConvert).setScale(2, RoundingMode.UP);
        return volumeDisponivel.doubleValue();
    }

    public Double volumeUtilizadoDoDisco() {
        Double volumeConvert = tamanhoTotalDoDisco() - volumeDisponivelDoDisco();
        BigDecimal volumeUtilizado = new BigDecimal(volumeConvert).setScale(2, RoundingMode.UP);
        return volumeUtilizado.doubleValue();
    }

    public Double taxaDeTransferenciaDisco() {
        Double timeConvert = Utils.converterMillisecondsToSeconds((double) looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia());
        BigDecimal timeSeconds = new BigDecimal(timeConvert).setScale(2, RoundingMode.UP);
        return timeSeconds.doubleValue();
    }

    public Disco getDisco(Integer index) {
        return disco.getDiscos().get(index);
    }

    public void insertDiscoLeitor(Integer index, Integer fk_aeroporto) {
        maquinaLeitor maquinaleitor = new maquinaLeitor();

        Disco disco = getDisco(index);

        // INSTANCIANDO CONNECTION, É ONDE TEM TODOS OS CAMPOS DE CONFIGURAÇÃO DA CONEXÃO COM OS BANCOS DE DADOS.
        Connection config = new Connection();

        // 🎲 SCRIPTS SQL 🎲
        String insert = "INSERT INTO disco ( nome, modelo, fk_maquina) VALUES ( ?,?,?);";
        String select = "SELECT * from maquina where hostname = ? and fk_aeroporto = ?";

// SQL SERVER  ------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        // INSTANCIANDO LISTA E SEU CONTEÚDO=SELECT DO BANCO AZURE
        // EFETUANDO O SCRIPT SELECT NO Template(ObjetoSQL Azure), isto está em Connection...
        List<Maquina> maquinas = template.query(select, new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName(), fk_aeroporto);
// SQL SERVER  ------------------
        // EFETUANDO O SCRIPT NO ObjetoSQL(Azure)...
        template.update(insert,
                disco.getNome(),
                disco.getModelo(),
                maquinas.get(0).getId_maquina()
        );

// SQL LOCAL  --------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        try {
            JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

            // INSTANCIANDO LISTA E SEU CONTEÚDO=SELECT DO BANCO LOCAL
            // EFETUANDO O SCRIPT SELECT NO TemplateLocal(ObjetoSQL Local), isto está em Connection...
            List<Maquina> maquinasLocal = templateLocal.query(select, new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName(), fk_aeroporto);

            for (Maquina maquina1 : maquinas) {
                System.out.println("maquinas: " + maquina1);
            }
// SQL LOCAL  --------------------
            // EFETUANDO O SCRIPT NO ObjetoSQL(Local)...
            templateLocal.update(insert,
                    disco.getNome(),
                    disco.getModelo(),
                    maquinasLocal.get(0).getId_maquina()
            );
        } catch (Exception e) {
            System.out.println("DEU ERRO AO INSERIR COMPONENTE DISCO EM DISCOLEITOR");
            System.out.println(e.getMessage());
        }

    }

    public List<com.mycompany.airvision.Disco> selectDiscos() {

        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());
        String select = "select * from Disco";

        return template.query(select, new BeanPropertyRowMapper(com.mycompany.airvision.Disco.class));

    }
}
