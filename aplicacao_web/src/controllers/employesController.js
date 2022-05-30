var employesModel = require("../models/employesModel");

function buscarFuncionarios(req, res) {
  var fk_aeroporto = req.params.fk_aeroporto;

  console.log(`Buscando Funcionarios AQUI É A CONTROLER`);

  employesModel
    .buscarFuncionariosSelect(fk_aeroporto)
    .then(function (resultado) {
      if (resultado.length > 0) {
        res.status(200).json(resultado);
      } else {
        res.status(204).send("Nenhum resultado encontrado!");
      }
    })
    .catch(function (erro) {
      console.log(erro);
      console.log(
        "Houve um erro ao buscar as ultimas medidas.",
        erro.sqlMessage
      );
      res.status(500).json(erro.sqlMessage);
    });
}

function buscarFuncionariosGestor(req, res) {
  var fk_aeroporto = req.params.fk_aeroporto;

  console.log(`Buscando Funcionarios AQUI É A CONTROLER`);

  employesModel
    .buscarFuncionariosSelectGestor(fk_aeroporto)
    .then(function (resultado) {
      if (resultado.length > 0) {
        res.status(200).json(resultado);
      } else {
        res.status(204).send("Nenhum resultado encontrado!");
      }
    })
    .catch(function (erro) {
      console.log(erro);
      console.log(
        "Houve um erro ao buscar as ultimas medidas.",
        erro.sqlMessage
      );
      res.status(500).json(erro.sqlMessage);
    });
}

module.exports = {
  buscarFuncionarios,
  buscarFuncionariosGestor,
};
