

function atualizarSideBar() {
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
        <span class="title ativo">Monitoramento</span> </span>
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
    <li id="opcao_mensagem">
      <a href="./mensagem.html">
        <span class="icon"> <img class="icons" src="imgDash/icons/email.png"></span>
        <span class="title">Mensagem</span></span>
      </a>
    </li>
    <li id="opcao_configuracoes">
      <a href="./configuracoes.html">
        <span class="icon"> <img class="icons" src="imgDash/icons/setting-gears.png"></span>
        <span class="title">Configurações</span></span>
      </a>
    </li>
    <li id="opcao_sair">
      <a href="../index.html">
        <span class="icon"> <img class="icons" src="imgDash/icons/exit.png"></span>
        <span class="title">Sair</span></span>
      </a>
    </li>
  </ul>
    `;
}
