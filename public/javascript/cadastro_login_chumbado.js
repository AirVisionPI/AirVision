// CADASTRO DE ADMIN NA TELA INICIAL
function btnCadastrar() {
  let formulario = new URLSearchParams(
    new FormData(document.getElementById("form_cadastro"))
  );
  let nome = formulario.get("nome");
  let email = formulario.get("email");
  let senha = formulario.get("senha");
  let razao_social = formulario.get("razao_social");
  let cnpj = formulario.get("cnpj");
  let local_companhia = formulario.get("local_companhia");

  const myBD = JSON.parse(localStorage.getItem("myBD"));

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
  if (myBD) {
    localStorage.setItem(
      "myBD",
      JSON.stringify([
        ...myBD,
        {
          id: myBD.at(-1).id + 1,
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
  } else {
    localStorage.setItem(
      "myBD",
      JSON.stringify([
        {
          id: 1,
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

  const myBD = JSON.parse(localStorage.getItem("myBD"));

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
  if (myBD) {
    localStorage.setItem(
      "myBD",
      JSON.stringify([
        ...myBD,
        {
          id: myBD.at(-1).id + 1,
          nome,
          email,
          senha,
          aeroporto_trabalho,
          cargo,
        },
      ])
    );
    alert(`Cadastro Efetuado com Sucesso!`);
  } else {
    localStorage.setItem(
      "myBD",
      JSON.stringify([
        {
          id: 1,
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
}
