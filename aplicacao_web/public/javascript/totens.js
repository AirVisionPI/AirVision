// =========================================
// -------------DADOS DO BANCO--------------
// =========================================
// ESSES OBJETOS SERÃO DO BANCO DE DADOS MEUS AMGIOS!
const totens = [
  {
    idTotem: "1",
    nomeTotem: "Totem_A",
    cpuTotem: "52%",
    ramTotem: "42%",
    discoTotem: "23%",
    statusTotem: "Online",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "2",
    nomeTotem: "Totem_B",
    cpuTotem: "62%",
    ramTotem: "35%",
    discoTotem: "63%",
    statusTotem: "Offline",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "3",
    nomeTotem: "Totem_C",
    cpuTotem: "55%",
    ramTotem: "23%",
    discoTotem: "53%",
    statusTotem: "Online",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "4",
    nomeTotem: "Totem_D",
    cpuTotem: "68%",
    ramTotem: "55%",
    discoTotem: "53%",
    statusTotem: "Offline",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "5",
    nomeTotem: "Totem_C",
    cpuTotem: "23%",
    ramTotem: "53%",
    discoTotem: "33%",
    statusTotem: "Online",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "6",
    nomeTotem: "Totem_D",
    cpuTotem: "52%",
    ramTotem: "54%",
    discoTotem: "42%",
    statusTotem: "Online",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "7",
    nomeTotem: "Totem_E",
    cpuTotem: "23%",
    ramTotem: "33%",
    discoTotem: "52%",
    statusTotem: "Online",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "8",
    nomeTotem: "Totem_F",
    cpuTotem: "42%",
    ramTotem: "60%",
    discoTotem: "43%",
    statusTotem: "Offline",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "9",
    nomeTotem: "Totem_G",
    cpuTotem: "32%",
    ramTotem: "66%",
    discoTotem: "33%",
    statusTotem: "Offline",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "10",
    nomeTotem: "Totem_H",
    cpuTotem: "78%",
    ramTotem: "53%",
    discoTotem: "64%",
    statusTotem: "Offline",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "11",
    nomeTotem: "Totem_I",
    cpuTotem: "12%",
    ramTotem: "42%",
    discoTotem: "63%",
    statusTotem: "Online",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
  {
    idTotem: "12",
    nomeTotem: "Totem_J",
    cpuTotem: "32%",
    ramTotem: "43%",
    discoTotem: "54%",
    statusTotem: "Offline",
    graficoCpu: "myChart-1",
    graficoRam: "",
    graficoDisco: "",
  },
];

// =========================================
// --------------FUNÇÃO ONLOAD--------------
// =========================================
function atualizarPainel() {
  totens.forEach(
    (
      { idTotem, nomeTotem, cpuTotem, discoTotem, ramTotem, statusTotem },
      index
    ) => {
      let statusClass;
      if (statusTotem == "Offline") {
        statusClass = "status return";
      } else if (statusTotem == "Online") {
        statusClass = "status delivered";
      }

      tbody_painel.innerHTML += `
          <tr onclick="exibirPainelDeControle()">
          <!--NOME DO TOTEM--->
           <td class="chart" id="nome_totem_${index}">${nomeTotem}</td>
    
          <!--ID DO TOTEM--->
           <td class="myChart" id="id_totem_${index}">${idTotem}</td>
          
          <!--CPU DO TOTEM--->
           <td class="chart" id="cpu_id_totem_${index}">${cpuTotem}</td>
    
          <!--RAM DO TOTEM--->
            <td class="chart" id="ram_id_totem_${index}">${ramTotem}</td> 
    
          <!--DISCO DO TOTEM--->
            <td class="chart" id="disco_totem_${index}">${discoTotem}</td>
    
          <!--STATUS DO TOTEM--->
            <td class="chart" id="status_totem_${index}"><span class="${statusClass}">${statusTotem}</td></span></td>
          </tr>
          `;
    }
  );

  // document
  //   .querySelectorAll(".chart")
  //   .forEach((td) =>
  //     td.addEventListener("mouseover", () => console.log("HOVERR"))
  //   );
}
