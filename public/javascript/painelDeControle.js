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
  statusPainel = false;
}