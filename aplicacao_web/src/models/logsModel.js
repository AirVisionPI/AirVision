var database = require("../database/config");

function buscarLogs(fk_maquina) {

  instrucaoSql = `SELECT
                    maquina.id_maquina as idMaquina,
                    maquina.hostname as hostMaquina,
                    logs_cpu.em_uso as cpuPorcentagem,
                    memoria.total as ramGBTotal,
                    logs_memoria.ram_disponivel as ramGBDisponivel,
                    logs_memoria.ram_uso as ramGBuso,
                    logs_memoria.ram_porcentagem as ramPorcentagem,
                    logs_disco.tamanho_do_volume as discoTotal,
                    logs_disco.volume_utilizado as discoUso,
                    logs_disco.volume_disponivel as discoDisponivel,
                    logs_disco.time_res_seconds as discoTimeRes,
                    logs_disco.data_hora as dataLogs
                  FROM maquina
                    JOIN cpu ON cpu.fk_maquina = maquina.id_maquina
                    JOIN logs_cpu ON logs_cpu.fk_cpu = cpu.id_cpu
                    JOIN memoria ON memoria.fk_maquina = maquina.id_maquina
                    JOIN logs_memoria ON logs_memoria.fk_memoria = memoria.id_memoria
                    JOIN disco ON disco.fk_maquina = maquina.id_maquina
                    JOIN logs_disco ON logs_disco.fk_disco = disco.id_disco
                  WHERE maquina.id_maquina = ${fk_maquina}
                  ORDER BY 
                    id_logs_cpu DESC,
                    id_logs_memoria DESC,
                    id_logs_disco DESC,
                    logs_cpu.data_hora,
                    logs_disco.data_hora,
                    logs_memoria.data_hora
                  OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY;`;
              
  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

function buscarMaquinas(fk_aeroporto) {
  instrucaoSql = `SELECT id_maquina FROM maquina WHERE maquina.fk_aeroporto = ${fk_aeroporto};`;
  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

module.exports = {
  buscarLogs,
  buscarMaquinas,
};
