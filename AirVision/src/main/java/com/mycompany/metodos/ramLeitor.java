/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metodos;

import com.mycompany.database.Connection;
import com.github.britooo.looca.api.core.Looca;
import com.mycompany.airvision.Maquina;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mycompany.airvision.memoria;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author jsantos
 */
public class ramLeitor {

    private Looca looca;

    public ramLeitor() {
        this.looca = new Looca();
    }

    public Double total() {
        Double ramConvert = Utils.converterByteToGigabyte((double) looca.getMemoria().getTotal());
        BigDecimal tamanhoDaRam = new BigDecimal(ramConvert).setScale(2, RoundingMode.UP);
        return tamanhoDaRam.doubleValue();
    }

    public Double emUso() {
        Double ramConvert = Utils.converterByteToGigabyte((double) looca.getMemoria().getEmUso());
        BigDecimal ramUtilizado = new BigDecimal(ramConvert).setScale(2, RoundingMode.UP);
        return ramUtilizado.doubleValue();
    }

    public Double disponivel() {
        Double ramConvert = (double) total() - emUso();
        BigDecimal ramDisponivel = new BigDecimal(ramConvert).setScale(2, RoundingMode.UP);
        return ramDisponivel.doubleValue();
    }

    public Double ramPorcentagemDeUso() {
        Double ramConvert = (double) emUso() * 100 / total();
        BigDecimal ramPorcentagem = new BigDecimal(ramConvert).setScale(2, RoundingMode.UP);
        return ramPorcentagem.doubleValue();
    }

    public void insertRam(Integer fk_aeroporto) {
        maquinaLeitor maquinaleitor = new maquinaLeitor();

        // INSTANCIANDO CONNECTION, É ONDE TEM TODOS OS CAMPOS DE CONFIGURAÇÃO DA CONEXÃO COM OS BANCOS DE DADOS.
        Connection config = new Connection();

        // 🎲 SCRIPTS SQL 🎲
        String insert = "INSERT INTO memoria ( total, fk_maquina) VALUES ( ?,?);";
        String select = "SELECT * from maquina where hostname = ? and fk_aeroporto = ?";

// SQL SERVER  ------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        // INSTANCIANDO LISTA E SEU CONTEÚDO=SELECT DO BANCO AZURE
        // EFETUANDO O SCRIPT SELECT NO Template(ObjetoSQL Azure), isto está em Connection...
        List<Maquina> maquinas = template.query(select, new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName(), fk_aeroporto);

        // EFETUANDO O SCRIPT NO ObjetoSQL(Azure)...
        template.update(insert,
                total(),
                maquinas.get(0).getId_maquina()
        );

// SQL LOCAL  --------------------
        // INSTANCIANDO O JDBCTemplate! (Faz Funcionar Select's Insert's Update's Delete's)
        // O que define se vai ser Local ou Server é o tipo de configuração retornada em getDataSource...
        JdbcTemplate templateLocal = new JdbcTemplate(config.getDataSourceLocal());

        // INSTANCIANDO LISTA E SEU CONTEÚDO=SELECT DO BANCO LOCAL
        // EFETUANDO O SCRIPT SELECT NO TemplateLocal(ObjetoSQL Local), isto está em Connection...
        List<Maquina> maquinasLocal = templateLocal.query(select, new BeanPropertyRowMapper<>(Maquina.class), maquinaleitor.getHostName(), fk_aeroporto);

        // EFETUANDO O SCRIPT NO ObjetoSQL(Local)...
        templateLocal.update(insert,
                total(),
                maquinasLocal.get(0).getId_maquina()
        );
    }

    public List<memoria> selectRam() {
        Connection config = new Connection();
        JdbcTemplate template = new JdbcTemplate(config.getDataSource());

        String select = "Select * from Ram;";

        return template.query(select, new BeanPropertyRowMapper(memoria.class));

    }

}
