var formulario;

function btnEntrarResponsivo() {
  formulario = new URLSearchParams(
    new FormData(document.getElementById("singin"))
  );
  btnEntrar();
}

function btnEntrarTradicional() {
  formulario = new URLSearchParams(
    new FormData(document.getElementById("form_login"))
  );
  btnEntrar();
}

function btnEntrar() {
  var email = formulario.get("email");
  var senha = formulario.get("senha");

  // ==========================================================================
  // ============ NO BANCO DE DADOS MYSQL =====================================
  // ==========================================================================
  if (email == "" || senha == "") {
    window.alert("Preencha todos os campos para prosseguir!");
    return false;
  }

  if (email.indexOf("@") == -1 || email.indexOf(".com") == -1) {
    window.alert("Ops, e-mail inválido! Verifique e tente novamente.");
    return false;
  }

  fetch("/usuarios/autenticar", {
    method: "POST",
    body: formulario,
  })
    .then(function (resposta) {
      console.log("ESTOU NO THEN DO entrar()!");

      if (resposta.ok) {
        if (resposta.length == 0) {
          window.alert("Cadastro incorreto ou inexistente");
        }
        console.log(resposta);

        resposta.json().then((json) => {
          console.log(json);
          console.log(JSON.stringify(json));

          sessionStorage.email = json.email_usuario;
          sessionStorage.nome = json.nome_usuario;
          sessionStorage.cargo = json.cargo_usuario;
          sessionStorage.id = json.id_usuario;
          sessionStorage.idAeroporto = json.id_aeroporto;
          sessionStorage.razaoAeroporto = json.razao_aeroporto;
          sessionStorage.cnpjAeroporto = json.cnpj_aeroporto;
          sessionStorage.responsavelAeroporto = json.responsavel_aeroporto;
          sessionStorage.localidadeAeroporto = json.localidade_aeroporto;

          setTimeout(function () {
            window.alert(`Seja bem vindo ${sessionStorage.nome}!`);
            window.location = "./Dashboard/monitoramento.html";
          }, 500); // apenas para exibir o loading
        });
      } else {
        window.alert("Cadastro incorreto ou inexistente!");
        console.log("Houve um erro ao tentar realizar o login!");

        resposta.text().then((texto) => {
          console.error(texto);
        });
      }
    })
    .catch(function (erro) {
      console.log(erro);
    });

  return false;

  // ==========================================================================
  // ============ CHUMBADO NO LOCAL STORAGE ===================================
  // ==========================================================================
  // try {
  //   const myBD = JSON.parse(localStorage.getItem("myBD"));

  //   const verificacaoLogin = myBD.find(
  //     (registro) => registro.email === email && registro.senha === senha
  //   );

  //   if (verificacaoLogin) {
  //     alert(`Bem Vindo ${verificacaoLogin.nome}`);
  //     sessionStorage.email = verificacaoLogin.email;
  //     sessionStorage.cargo = verificacaoLogin.cargo;
  //     window.location = "./Dashboard/monitoramento.html";
  //   } else {
  //     alert(`Cadastro incorreto ou inexistente`);
  //   }
  // } catch (error) {
  //   console.error(error);
  //   alert(`Cadastro incorreto ou inexistente`);
  // }
}
