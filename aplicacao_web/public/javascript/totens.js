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
        requestLogs();
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados: ${error.message}`);
      requestLogs();
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
        if (segundos > 25) {
          statusTotem = "Offline";
          statusClass = "status return";
        } else {
          statusTotem = "Online";
          statusClass = "status delivered";
        }

        var corCpuPorcentagem = "";
        var corRamPorcentagem = "";
        var corDiscoPorcentagem = "";
        var emojiCpu = "";
        var emojiRam = "";
        var emojiDisco = "";

        // CPU ALERTA COR
        if (cpuPorcentagem < 20) {
          corCpuPorcentagem = "#3CB371";
          emojiCpu = "✅";
        } else if (cpuPorcentagem < 60) {
          corCpuPorcentagem = "#FFD700";
          emojiCpu = "🟡";
        } else {
          corCpuPorcentagem = "#FF0000";
          emojiCpu = "❗";
        }

        // RAM ALERTA COR
        if (ramPorcentagem < 40) {
          corRamPorcentagem = "#3CB371";
          emojiRam = "✅";
        } else if (ramPorcentagem < 70) {
          corRamPorcentagem = "#FFD700";
          emojiRam = "🟡";
        } else {
          corRamPorcentagem = "#FF0000";
          emojiRam = "❗";
        }

        // DISCO ALERTA COR
        if (discoTimeRes < 40) {
          corDiscoPorcentagem = "#3CB371";
          emojiDisco = "✅";
        } else if (discoTimeRes < 80) {
          corDiscoPorcentagem = "#FFD700";
          emojiDisco = "🟡";
        } else {
          corDiscoPorcentagem = "#FF0000";
          emojiDisco = "❗";
        }

        tbody_painel.innerHTML += `
          <tr onclick="exibirPainelDeControle(${idMaquina})">
          <!--NOME DO TOTEM--->
           <td class="chart" id="nome_totem_${index}">${hostMaquina}</td>
    
          <!--ID DO TOTEM--->
           <td class="myChart" id="id_totem_${index}">${idMaquina}</td>
          
          <!--CPU DO TOTEM--->
           <td class="chart" style="color: ${corCpuPorcentagem}" id="cpu_id_totem_${index}">${cpuPorcentagem}${emojiCpu}</td>
    
          <!--RAM DO TOTEM--->
            <td class="chart" style="color: ${corRamPorcentagem}" id="ram_id_totem_${index}">${ramPorcentagem}${emojiRam}</td> 
    
          <!--DISCO DO TOTEM--->
            <td class="chart" style="color: ${corDiscoPorcentagem}" id="disco_totem_${index}">${discoTimeRes}${emojiDisco}</td>
    
          <!--STATUS DO TOTEM--->
            <td class="chart" id="status_totem_${index}"><span class="${statusClass}">${statusTotem}</td></span></td>
          </tr>
          `;
      }
    );

  setTimeout(() => requestLogs(), 4000);
}
