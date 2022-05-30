let statusPainel = false;

function requestMaquina() {
  var id_maquina = document.getElementById()

  fetch(`/logs/table/${fk_aeroporto}`)
    .then(function (response) {
      if (response.ok) {
        response.json().then(function (resposta) {
          console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
          tbody_painel.innerHTML = "";
          atualizarPainel(resposta);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados: ${error.message}`);
    });
}

function exibirPainelDeControle() {
  painelDeControle.style.width = "300%";
  containerDoPainel.style.display = "block";
  statusPainel = true;
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
