var database = require("../database/config");

function buscarFuncionariosSelect(fk_aeroporto) {
  instrucaoSql = `select * from usuario where fk_aeroporto = ${fk_aeroporto} and cargo_usuario != 'admin'`;
  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

function buscarFuncionariosSelectGestor(fk_aeroporto) {
  instrucaoSql = `select * from usuario where fk_aeroporto = ${fk_aeroporto} and cargo_usuario = 'tecnico'`;
  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

module.exports = {
  buscarFuncionariosSelect,
  buscarFuncionariosSelectGestor,
};
