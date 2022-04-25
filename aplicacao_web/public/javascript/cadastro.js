// ==========================================================================
// ============ CADASTRO ADMIN ==============================================
// ==========================================================================
function btnCadastrar() {
  let formulario = new URLSearchParams(
    new FormData(document.getElementById("form_cadastro"))
  );
  let nome = formulario.get("nome").trim();
  let email = formulario.get("email").trim();
  let senha = formulario.get("senha").trim();
  let confirmacao_senha = formulario.get("confirmacao_senha").trim();
  let razao_social = formulario.get("razao_social").trim();
  let cnpj = formulario.get("cnpj").trim();
  let local_companhia = formulario.get("local_companhia").trim();

  
  // VALIDAÇÃO
  if (nome == "" || email == "" || senha == "" || confirmacao_senha == "" || razao_social == "" || cnpj == "" || local_companhia == "") {
    window.alert("Preencha todos os campos para continuar!");
    return false;
  }
  
  if (email.indexOf("@") == -1 || email.indexOf(".com") == -1) {
    window.alert("Ops, e-mail inválido! Verifique e tente novamente.");
    return false;
  }
  
  if (senha != confirmacao_senha) {
    window.alert("As senhas inseridas devem ser iguais para continuar!");
    return false;
  }
  
  // ==========================================================================
  // ============ NO BANCO DE DADOS MYSQL =====================================
  // ==========================================================================
  
  fetch("/usuarios/cadastrar", {
    method: "POST",
    body: formulario
  }).then(function (resposta) {
    
    console.log("resposta: ", resposta);
    
    if (resposta.ok) {
      window.alert("Cadastro realizado com sucesso!");
      window.location = "login.html";
    } else {
      throw ("Houve um erro ao tentar realizar o cadastro!");
    }
  }).catch(function (resposta) {
    console.log(`#ERRO: ${resposta}`);
  });
  
  return false;
  
  // ==========================================================================
  // ============ CHUMBADO NO LOCAL STORAGE ===================================
  // ==========================================================================
  // const myBD = JSON.parse(localStorage.getItem("myBD")) || [];
  // localStorage.setItem(
    //   "myBD",
  //   JSON.stringify([
  //     ...myBD,
  //     {
  //       id: (myBD.at(-1)?.id ?? 0) + 1,
  //       nome,
  //       email,
  //       senha,
  //       razao_social,
  //       cnpj,
  //       local_companhia,
  //       cargo: "admin",
  //     },
  //   ])
  // );
  // alert(`Cadastro Efetuado com Sucesso!`);
}

// CADASTRO DE FUNCIONARIO E GESTOR PELO ADMIN NA TELA DASHBOARD
function btnCadastrarFunc() {
  let formulario = new URLSearchParams(
    new FormData(document.getElementById("form_funcionario"))
  );
  let nome = formulario.get("nome");
  let email = formulario.get("email");
  let senha = formulario.get("senha");
  let aeroporto_trabalho = formulario.get("aeroporto_trabalho");
  let cargo = formulario.get("cargo");

  const myBD = JSON.parse(localStorage.getItem("myBD")) || [];

  // VALIDAÇÃO
  if (
    nome == "" ||
    email == "" ||
    senha == "" ||
    aeroporto_trabalho == "" ||
    cargo == ""
  ) {
    alert("Preencha corretamente os dados do fomulário");
    return;
  }

  // ESSA PARTE FAZ O CADASTRO NO LOCAL STORAGE
  localStorage.setItem(
    "myBD",
    JSON.stringify([
      ...myBD,
      {
        id: (myBD.at(-1)?.id ?? 0) + 1,
        nome,
        email,
        senha,
        aeroporto_trabalho,
        cargo,
      },
    ])
  );
  alert(`Cadastro Efetuado com Sucesso!`);
}
