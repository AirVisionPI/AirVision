// =========================================
// -------------DADOS DO BANCO--------------
// =========================================
// ESSES OBJETOS SERÃO DO BANCO DE DADOS MEUS AMGIOS!

function requestLogs() {
  var fk_aeroporto = sessionStorage.idAeroporto;

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

// =========================================
// --------------FUNÇÃO ONLOAD--------------
// =========================================
function atualizarPainel(resposta) {
  resposta
    .sort((a, b) => new Date(b.dataLogs) - new Date(a.dataLogs))
    .forEach(
      (
        {
          idMaquina,
          hostMaquina,
          cpuPorcentagem,
          discoTimeRes,
          ramPorcentagem,
          dataLogs,
        },
        index
      ) => {
        let statusClass;
        moment.tz.setDefault("Europe/Moscow");

        var momentData = moment().diff(moment(dataLogs));

        var segundos = moment.duration(momentData).asSeconds().toFixed();

        console.log(segundos);

        var statusTotem;
        if (segundos > 20) {
          statusTotem = "Offline";
          statusClass = "status return";
        } else {
          statusTotem = "Online";
          statusClass = "status delivered";
        }

        tbody_painel.innerHTML += `
          <tr onclick="exibirPainelDeControle()" id="maquina_${idMaquina}">
          <!--NOME DO TOTEM--->
           <td class="chart" id="nome_totem_${index}">${hostMaquina}</td>
    
          <!--ID DO TOTEM--->
           <td class="myChart" id="id_totem_${index}">${idMaquina}</td>
          
          <!--CPU DO TOTEM--->
           <td class="chart" id="cpu_id_totem_${index}">${cpuPorcentagem}</td>
    
          <!--RAM DO TOTEM--->
            <td class="chart" id="ram_id_totem_${index}">${ramPorcentagem}</td> 
    
          <!--DISCO DO TOTEM--->
            <td class="chart" id="disco_totem_${index}">${discoTimeRes}</td>
    
          <!--STATUS DO TOTEM--->
            <td class="chart" id="status_totem_${index}"><span class="${statusClass}">${statusTotem}</td></span></td>
          </tr>
          `;
      }
    );

  setTimeout(() => requestLogs(), 2000);
}
