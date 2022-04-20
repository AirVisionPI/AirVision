// CADASTRO DE ADMIN NA TELA INICIAL
function btnCadastrar() {
  let formulario = new URLSearchParams(
    new FormData(document.getElementById("form_cadastro"))
  );
  let nome = formulario.get("nome").trim();
  let email = formulario.get("email").trim();
  let senha = formulario.get("senha").trim();
  let razao_social = formulario.get("razao_social").trim();
  let cnpj = formulario.get("cnpj").trim();
  let local_companhia = formulario.get("local_companhia").trim();

  const myBD = JSON.parse(localStorage.getItem("myBD")) || [];

  // VALIDAÇÃO
  if (
    nome == "" ||
    email == "" ||
    senha == "" ||
    razao_social == "" ||
    cnpj == "" ||
    local_companhia == ""
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
        razao_social,
        cnpj,
        local_companhia,
        cargo: "admin",
      },
    ])
  );
  alert(`Cadastro Efetuado com Sucesso!`);
}

// CADASTRO DE FUNCIONARIO E GESTOR PELO ADMIN NA TELA INICIAL
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
