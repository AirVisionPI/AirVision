var usuarioModel = require("../models/usuarioModel");

var sessoes = [];

function testar(req, res) {
    console.log("ENTRAMOS NA usuarioController");
    res.json("ESTAMOS FUNCIONANDO!");
}

function entrar(req, res) {
    var email = req.body.email;
    var senha = req.body.senha;

    if (email == undefined) {
        res.status(400).send("Seu email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("Sua senha está indefinida!");
    } else {
        usuarioModel.entrar(email, senha)
            .then(
                function (resultado) {
                    console.log(`\nResultados encontrados: ${resultado.length}`);
                    console.log(`Resultados: ${JSON.stringify(resultado)}`); // transforma JSON em String

                    if (resultado.length == 1) {
                        console.log(resultado);
                        res.json(resultado[0]);
                    } else if (resultado.length == 0) {
                        res.status(403).send("Email e/ou senha inválido(s)");
                    } else {
                        res.status(403).send("Mais de um usuário com o mesmo login e senha!");
                    }
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log("\nHouve um erro ao realizar o login! Erro: ", erro.sqlMessage);
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }

}

function cadastrar(req, res) {
    var nome = req.body.nome;
    var razao_social = req.body.razao_social;
    var cnpj = req.body.cnpj;
    var local_companhia = req.body.local_companhia;

    if (nome == undefined) {
        res.status(400).send("Seu Nome está undefined!");
    } else if (razao_social == undefined) {
        res.status(400).send("Sua razão social está undefined!");
    } else if (cnpj == undefined) {
        res.status(400).send("Seu CNPJ está undefined!");
    } else if (local_companhia == undefined) {
        res.status(400).send("Seu Local Companhia está undefined!");
    } else {
        usuarioModel.cadastrar(nome, razao_social, cnpj, local_companhia)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro comp! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}

function cadastrarUser(req, res) {
    var nome = req.body.nome;
    var email = req.body.email;
    var senha = req.body.senha;
    var razao_social = req.body.razao_social;
    var cnpj = req.body.cnpj;
    var local_companhia = req.body.local_companhia;

    if (nome == undefined) {
        res.status(400).send("Seu Nome está undefined!");
    } else if (email == undefined) {
        res.status(400).send("Seu Email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("Sua Senha está undefined!");
    } else if (razao_social == undefined) {
        res.status(400).send("Sua razão social está undefined!");
    } else if (cnpj == undefined) {
        res.status(400).send("Seu CNPJ está undefined!");
    } else if (local_companhia == undefined) {
        res.status(400).send("Seu Local Companhia está undefined!");
    } else {
        usuarioModel.cadastrarUser(nome, email, senha, razao_social, cnpj, local_companhia)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro user! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}


function cadastrarFuncionario(req, res) {
    var nome = req.body.nome;
    var email = req.body.email;
    var senha = req.body.senha;
    var aeroporto_trabalho = req.body.aeroporto_trabalho;
    var cargo = req.body.cargo;

    if (nome == undefined) {
        res.status(400).send("O nome está undefined!");
    } else if (email == undefined) {
        res.status(400).send("O email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("A senha está undefined!");
    } else if (aeroporto_trabalho == undefined) {
        res.status(400).send("O local de trabalho está undefined!");
    } else if (cargo == undefined) {
        res.status(400).send("O cargo está undefined!");
    } else {
        usuarioModel.cadastrarFuncionario(nome, email, senha, aeroporto_trabalho, cargo)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}


module.exports = {
    entrar,
    cadastrar,
    cadastrarUser,
    cadastrarFuncionario,
    testar,
}