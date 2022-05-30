var logsModel = require("../models/logsModel");

function buscarLogs(req, res) {
  var fk_aeroporto = req.params.fk_aeroporto;

  console.log(`Buscando LOGS AQUI É A CONTROLER`);

  logsModel
    .buscarMaquinas(fk_aeroporto)
    .then(async (maquinas) => {
      const todosOsLogs = await Promise.all(
        maquinas.map(async (maquina) => {
          const logs = await logsModel.buscarLogs(maquina.id_maquina);
          return logs[0];
        })
      );

      if (todosOsLogs.length > 0) {
        res.status(200).json(todosOsLogs);
      } else {
        res.status(204).send("Nenhum resultado encontrado!");
      }
    })
    .catch(function (error) {
      console.error(`Erro na Controller BUSCAR MAQUINAS: ${error.message}`);
    });
}

async function buscarLogsPainel(req, res) {
  var id_maquina = req.params.id_maquina;

  console.log(`Buscando LOGS AQUI É A CONTROLER`);
  const logs = await logsModel.buscarLogs(id_maquina);

  if (logs.length > 0) {
    res.status(200).json(logs);
  } else {
    res.status(204).send("Nenhum resultado encontrado!");
  }
}

module.exports = {
  buscarLogs,
  buscarLogsPainel,
};
