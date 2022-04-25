var database = require("../database/config")



function entrar(email, senha) {
    console.log("ACESSEI O LOGIN MODEL")
    var instrucao = `
        SELECT * FROM usuario WHERE email_usuario = '${email}' AND senha_usuario = '${senha}';
    `;
    console.log("Executando a instrução SQL: \n" + instrucao);
    return database.executar(instrucao);
}

function cadastrar(nome, email, senha, razao_social, cnpj, local_companhia) {
    console.log("ACESSEI O CADASTRO MODEL");

    var instrucao_companhia = `
        INSERT INTO companhia_aerea (razao_aeroporto, cnpj_aeroporto, responsavel_aeroporto, localidade_aeroporto) VALUES 
        ('${razao_social}', '${cnpj}', '', '${local_companhia}');
        `;
    console.log("Executando a instrução SQL: \n" + instrucao_companhia);    
    
    var instrucao_usuario = `
        INSERT INTO usuario (nome_usuario, email_usuario, senha_usuario, cargo_usuario, fk_aeroporto) VALUES 
        ('${nome}', '${email}', '${senha}', 'admin', (SELECT id_aeroporto FROM companhia_aerea WHERE razao_aeroporto = '${razao_social}' AND localidade_aeroporto = '${local_companhia}' AND cnpj_aeroporto = '${cnpj}'));
    `;
    console.log("Executando a instrução SQL: \n" + instrucao_usuario);

    var instrucao_update = `
        UPDATE companhia_aerea AS comp SET comp.responsavel_aeroporto = '${nome}' WHERE comp.id_aeroporto=(SELECT t.id_aeroporto FROM (SELECT id_aeroporto FROM companhia_aerea WHERE razao_aeroporto = '${razao_social}' AND cnpj_aeroporto = '${cnpj}' AND localidade_aeroporto = '${local_companhia}') AS t);
    `
    console.log("Executando a instrução SQL: \n" + instrucao_update);

    database.executar(instrucao_update);
    database.executar(instrucao_companhia);
    return database.executar(instrucao_usuario);
}

module.exports = {
    entrar,
    cadastrar,
};