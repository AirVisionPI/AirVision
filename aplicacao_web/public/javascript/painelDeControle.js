let statusPainel = false;

function exibirPainelDeControle() {
  painelDeControle.style.width = "300%";
  containerDoPainel.style.display = "block";
  statusPainel = true;
  if (statusPainel) {
    //Esconde as Legendas do PAINEL deixando somente o NOME TOTEM
    document
      .querySelectorAll(".td_painel")
      .forEach((td_painel) => (td_painel.style.display = "none"));

    //Esconde CPU, RAM, DISCO, STATUS
    document.querySelectorAll(".chart").forEach((td) => {
      if (!td.id.slice(0, -1).includes("nome_totem_")) {
        td.style.display = "none";
      }
    });

    //Esconde ID
    document
      .querySelectorAll(".myChart")
      .forEach((td) => (td.style.display = "none"));
  }
}

function esconderPainelDeControle() {
  painelDeControle.style.width = "0%";
  containerDoPainel.style.display = "none";
  statusPainel = false;
  if (!statusPainel) {
    table_totens.innerHTML = `
    <thead id="thead_painel">
      <tr>
        <td>Nome Totem</td>
        <td class="td_painel">ID</td>
        <td class="td_painel">% CPU</td>
        <td class="td_painel">% RAM</td>
        <td class="td_painel">% DISCO</td>
        <td class="td_painel">Status</td>
      </tr>
    </thead>
    <tbody id="tbody_painel">
      <script type="text/javascript" src="../javascript/configGraficos.js"></script>
    </tbody>
  `;
    atualizarPainel();
  }

  // ESSE DE BAIXO ESTÁ QUEBRANDO TODO STYLE DA PAGINA

  // if (!statusPainel) {
  //   //Esconde as Legendas do PAINEL deixando somente o NOME TOTEM
  //   document
  //     .querySelectorAll(".td_painel")
  //     .forEach((td_painel) => (td_painel.style.display = "block"));

  //   //Esconde CPU, RAM, DISCO, STATUS
  //   document.querySelectorAll(".chart").forEach((td) => {
  //     if (!td.id.slice(0, -1).includes("nome_totem_")) {
  //       td.style.display = "block";
  //     }
  //   });

  //   //Esconde ID
  //   document
  //     .querySelectorAll(".myChart")
  //     .forEach((td) => (td.style.display = "block"));
  // }
}
