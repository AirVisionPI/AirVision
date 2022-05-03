var database = require("../database/config")



function entrar(email, senha) {
    console.log("ACESSEI O LOGIN MODEL")
    var instrucao_usuario = `
        SELECT * FROM usuario as u 
        INNER JOIN companhia_aerea as c 
        ON u.fk_aeroporto = c.id_aeroporto
        WHERE u.email_usuario = '${email}' and u.senha_usuario = '${senha}';
    `;
    // SELECT DE LOUCO
    // SELECT * FROM usuario as u 
    // INNER JOIN companhia_aerea as c 
    // ON u.fk_aeroporto = 
    // (SELECT t.fk_aeroporto FROM (SELECT fk_aeroporto FROM usuario WHERE email_usuario = '${email}' AND senha_usuario = '${senha}') AS t) 
    // and c.id_aeroporto = 
    // (SELECT i.fk_aeroporto FROM (SELECT fk_aeroporto FROM usuario WHERE email_usuario = '${email}' AND senha_usuario = '${senha}') AS i)
    // and u.email_usuario = '${email}' and u.senha_usuario = '${senha}';

    // var instrucao_companhia = `
    //     SELECT * FROM companhia_aerea WHERE id_aeroporto = (SELECT fk_aeroporto FROM usuario WHERE email_usuario = '${email}' AND senha_usuario = '${senha}');
    // `;
    // database.executar(instrucao_companhia);
    return database.executar(instrucao_usuario);
}

function cadastrar(nome, email, senha, razao_social, cnpj, local_companhia) {
    console.log("ACESSEI O CADASTRO MODEL");

    async function instrucao2() {
        var instrucao_usuario = `
            INSERT INTO usuario (nome_usuario, email_usuario, senha_usuario, cargo_usuario, fk_aeroporto) VALUES 
            ('${nome}', '${email}', '${senha}', 'admin', 
                (SELECT id_aeroporto FROM companhia_aerea WHERE razao_aeroporto = '${razao_social}' AND localidade_aeroporto = '${local_companhia}' AND cnpj_aeroporto = '${cnpj}' AND responsavel_aeroporto = '${nome}')
            );
        `;
        console.log("Executando a instrução SQL: \n" + instrucao_usuario);
        return database.executar(instrucao_usuario);
    }

    function instrucao1() {
        var validar_parte_1 = false;
        var instrucao_companhia = `
            INSERT INTO companhia_aerea (razao_aeroporto, cnpj_aeroporto, responsavel_aeroporto, localidade_aeroporto) VALUES 
            ('${razao_social}', '${cnpj}', '${nome}', '${local_companhia}');
            `;
        console.log("Executando a instrução SQL: \n" + instrucao_companhia);
        db_exec();
        function db_exec() {
            database.executar(instrucao_companhia);
            validar_parte_1 = true;
        }

        if(validar_parte_1) {
            return instrucao2();
        }

        return instrucao1();
    }

    return instrucao1();
}

function cadastrarFuncionario(nome, email, senha, aeroporto_trabalho, cargo) {
    console.log("ACESSEI O CADASTRO MODEL");

    var instrucao_usuario = `
        INSERT INTO usuario (nome_usuario, email_usuario, senha_usuario, cargo_usuario, fk_aeroporto) VALUES 
        ('${nome}', '${email}', '${senha}', '${cargo}', '${aeroporto_trabalho}');
    `;
    console.log("Executando a instrução SQL: \n" + instrucao_usuario);

    // var instrucao_update = `
    //     UPDATE companhia_aerea AS comp SET comp.responsavel_aeroporto = '${nome}' WHERE comp.id_aeroporto=(SELECT t.id_aeroporto FROM (SELECT id_aeroporto FROM companhia_aerea WHERE razao_aeroporto = '${razao_social}' AND cnpj_aeroporto = '${cnpj}' AND localidade_aeroporto = '${local_companhia}') AS t);
    // `
    // console.log("Executando a instrução SQL: \n" + instrucao_update);

    // database.executar(instrucao_update);
    return database.executar(instrucao_usuario);
}

module.exports = {
    entrar,
    cadastrarFuncionario,
    cadastrar,
};