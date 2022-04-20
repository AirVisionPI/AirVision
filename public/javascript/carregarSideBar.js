function atualizarSideBar() {
  // console.log(window.location.href.split("/").at(-1).split(".")[0]);

  const htmlAtual = window.location.href.split("/").at(-1).split(".")[0];

  sideBarNavegation.innerHTML = `
    <ul>
    <li>
      <a href="./monitoramento.html">
        <span class="icon"><img class="icons" src="imgDash/icons/airplane.png"></span>
        <span class="title">
          <h2>AirVision</h2>
        </span>
      </a>
    </li>
    <li id="opcao_monitoramento">
      <a href="./monitoramento.html">
        <span class="icon"><img class="icons" src="imgDash/icons/monitoring.png"></i></span>
        <span class="title">Monitoramento</span> </span>
      </a>
    </li>
    <li id="opcao_visaoGeral">
      <a href="./visaoGeral.html">
        <span class="icon"><img class="icons" src="imgDash/icons/graphic.png  "></span>
        <span class="title">Visão Geral</span> </span>
      </a>
    </li>
    <li id="opcao_funcAtivos">
      <a href="./funcAtivos.html">
        <span class="icon"> <img class="icons" src="imgDash/icons/group.png"></i></span>
        <span class="title">Funcionários Ativos</span></span>
      </a>
    </li>
    <li id="opcao_cadastroFuncionario">
      <a href="./cadastroFuncionario.html">
        <span class="icon"> <img class="icons" src="imgDash/icons/billboard.png"></i></span>
        <span class="title">Cadastro Funcionário</span> </span>
      </a>
    </li>
    </li>
    <li id="opcao_sair">
      <a href="../index.html">
        <span class="icon"> <img class="icons" src="imgDash/icons/exit.png"></span>
        <span class="title">Sair</span></span>
      </a>
    </li>
  </ul>
    `;

  if (sessionStorage.cargo === "gestor") {
    opcao_visaoGeral.style.display = "block";
    opcao_monitoramento.style.display = "block";
    opcao_funcAtivos.style.display = "block";
    opcao_cadastroFuncionario.style.display = "none";
    // opcao_mensagem.style.display = "none";
    // opcao_configuracoes.style.display = "none";
    opcao_sair.style.display = "block";
  }

  if (sessionStorage.cargo === "admin") {
    opcao_visaoGeral.style.display = "block";
    opcao_monitoramento.style.display = "block";
    opcao_funcAtivos.style.display = "block";
    opcao_cadastroFuncionario.style.display = "block";
    // opcao_mensagem.style.display = "block";
    // opcao_configuracoes.style.display = "block";
    opcao_sair.style.display = "block";
  }

  if (sessionStorage.cargo === "tecnico") {
    opcao_visaoGeral.style.display = "none";
    opcao_monitoramento.style.display = "block";
    opcao_funcAtivos.style.display = "none";
    opcao_cadastroFuncionario.style.display = "none";
    // opcao_mensagem.style.display = "block";
    // opcao_configuracoes.style.display = "none";
    opcao_sair.style.display = "block";
  }

  document
    .querySelectorAll(`#opcao_${htmlAtual}`)[0]
    .children[0].children[1].classList.add("ativo");
}
