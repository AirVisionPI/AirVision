let statusPainel = false;

function requestMaquina(idMaquina) {
  fetch(`/logs/painel/${idMaquina}`)
    .then(function (response) {
      if (response.ok) {
        response.json().then(function (resposta) {
          console.log(`Dados recebidos PAINEL: ${JSON.stringify(resposta)}`);
          cpuPainelDoBanco.innerHTML = resposta[0].cpuPorcentagem;
          ramPainelDoBanco.innerHTML = resposta[0].ramPorcentagem;
          discoPainelDoBanco.innerHTML = resposta[0].discoTimeRes;
          nome_do_totem_painel_de_controle.innerHTML = resposta[0].hostMaquina;
          chartGraphCPU.data.datasets[0].data.shift();
          chartGraphCPU.data.datasets[0].data.push(resposta[0].cpuPorcentagem);
          chartGraphCPU.update();
          chartGraphRAM.data.datasets[0].data.shift();
          chartGraphRAM.data.datasets[0].data.push(resposta[0].ramPorcentagem);
          chartGraphRAM.update();
          chartGraphDISCO.data.datasets[0].data.shift();
          chartGraphDISCO.data.datasets[0].data.push(resposta[0].discoTimeRes);
          chartGraphDISCO.update();
          chartGraphVOLUME.data.datasets[0].data = [];
          chartGraphVOLUME.data.datasets[0].data = [
            resposta[0].discoUso,
            resposta[0].discoDisponivel,
          ];
          chartGraphVOLUME.update();
          setTimeout(() => requestMaquina(idMaquina), 15000);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados: ${error.message}`);
    });
}

function exibirPainelDeControle(idMaquina) {
  painelDeControle.style.width = "300%";
  containerDoPainel.style.display = "block";
  statusPainel = true;
  requestMaquina(idMaquina);
  esconderTabelaLogsTotem();
}

function esconderPainelDeControle() {
  painelDeControle.style.width = "0%";
  containerDoPainel.style.display = "none";
  statusPainel = false;
  exibirTabelaLogsTotem();
}

function exibirTabelaLogsTotem() {
  if (!statusPainel) {
    // Esconde as Legendas do PAINEL deixando somente o NOME TOTEM
    document
      .querySelectorAll(".td_painel")
      .forEach((td_painel) => (td_painel.style.visibility = "visible"));

    //Esconde CPU, RAM, DISCO, STATUS
    document.querySelectorAll(".chart").forEach((td) => {
      if (!td.id.slice(0, -1).includes("nome_totem_")) {
        td.style.visibility = "visible";
      }
    });

    //Esconde ID
    document
      .querySelectorAll(".myChart")
      .forEach((td) => (td.style.visibility = "visible"));
  }
}

function esconderTabelaLogsTotem() {
  if (statusPainel) {
    // Esconde as Legendas do PAINEL deixando somente o NOME TOTEM
    document
      .querySelectorAll(".td_painel")
      .forEach((td_painel) => (td_painel.style.visibility = "collapse"));

    //Esconde CPU, RAM, DISCO, STATUS
    document.querySelectorAll(".chart").forEach((td) => {
      if (!td.id.slice(0, -1).includes("nome_totem_")) {
        td.style.visibility = "collapse";
      }
    });

    //Esconde ID
    document
      .querySelectorAll(".myChart")
      .forEach((td) => (td.style.visibility = "collapse"));
  }
}
