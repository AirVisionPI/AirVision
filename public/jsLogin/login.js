// ===================================================================================== //
//                                   CSS/STYLE EFFECTS                                   //
// ===================================================================================== //

// Moving box onclick effect
const signinBtn = document.querySelector(".signin-btn");
const signupBtn = document.querySelector(".signup-btn");
const content = document.querySelector(".content");

signupBtn.onclick = function () {
  content.classList.add("active");
};

signinBtn.onclick = function () {
  content.classList.remove("active");
};

// ===================================================================================== //
//                                  LOGIN DO USUÁRIO                                     //
// ===================================================================================== //

function limparFormulario() {
  document.getElementById("form_login").reset();
}

function entrar() {
  aguardar();

  var formulario = new URLSearchParams(
    new FormData(document.getElementById("form_login"))
  );

  var email = formulario.get("email");
  var senha = formulario.get("senha");

  console.log("FORM LOGIN: ", email);
  console.log("FORM SENHA: ", senha);

  // TODO: VERIFICAR AS VALIDAÇÕES QUE ELES ESTÃO APRENDENDO EM ALGORITMOS
  if (email == "" || senha == "") {
    window.alert("Preencha todos os campos para prosseguir!");
    finalizarAguardar();
    return false;
  }

  if (email.indexOf("@") == -1 || email.indexOf(".com") == -1) {
    window.alert("Ops, e-mail inválido! Verifique e tente novamente.");
    finalizarAguardar();
    return false;
  }

  fetch("/usuarios/autenticar", {
    method: "POST",
    body: formulario,
  })
    .then(function (resposta) {
      console.log("ESTOU NO THEN DO entrar()!");

      if (resposta.ok) {
        console.log(resposta);

        resposta.json().then((json) => {
          console.log(json);
          console.log(JSON.stringify(json));

          sessionStorage.EMAIL_USUARIO = json.email;
          sessionStorage.NOME_USUARIO = json.nome;
          sessionStorage.ID_USUARIO = json.id;

          setTimeout(function () {
            window.location = "./dashboard.html";
          }, 1000); // apenas para exibir o loading
        });
      } else {
        console.log("Houve um erro ao tentar realizar o login!");

        resposta.text().then((texto) => {
          console.error(texto);
          finalizarAguardar(texto);
        });
      }
    })
    .catch(function (erro) {
      console.log(erro);
    });

  return false;
}

// ===================================================================================== //
//                                  CADASTRO DO USUÁRIO                                     //
// ===================================================================================== //
// function limparFormulario() {
//   document.getElementById("form_cadastro").reset();
// }

// function cadastrar() {
//   aguardar();

//   var formulario = new URLSearchParams(
//     new FormData(document.getElementById("form_cadastro"))
//   );

//   var nome = formulario.get("nome");
//   var email = formulario.get("email");
//   var senha = formulario.get("senha");
//   var confirmacaoSenha = formulario.get("confirmacao-senha");

//   // TODO: VERIFICAR AS VALIDAÇÕES QUE ELES ESTÃO APRENDENDO EM ALGORITMOS
//   if (nome == "" || email == "" || senha == "" || confirmacaoSenha == "") {
//     window.alert("Preencha todos os campos para prosseguir!");
//     if (nome == "") {
//       console.log("nome está em branco");
//     }
//     if (email == "") {
//       console.log("email está em branco");
//     }
//     if (senha == "") {
//       console.log("senha está em branco");
//     }
//     if (confirmacaoSenha == "") {
//       console.log("confirmacaoSenha está em branco");
//     }
//     finalizarAguardar();
//     return false;
//   }

//   if (email.indexOf("@") == -1 || email.indexOf(".com") == -1) {
//     window.alert("Ops, e-mail inválido! Verifique e tente novamente.");
//     finalizarAguardar();
//     return false;
//   }

//   if (senha != confirmacaoSenha) {
//     window.alert("As senhas inseridas devem ser iguais para prosseguir!");
//     finalizarAguardar();
//     return false;
//   }

//   fetch("/usuarios/cadastrar", {
//     method: "POST",
//     body: formulario,
//   })
//     .then(function (resposta) {
//       console.log("resposta: ", resposta);

//       if (resposta.ok) {
//         window.alert("Cadastro realizado com sucesso!");
//         window.location = "login.html";
//         limparFormulario();
//         finalizarAguardar();
//       } else {
//         throw "Houve um erro ao tentar realizar o cadastro!";
//       }
//     })
//     .catch(function (resposta) {
//       console.log(`#ERRO: ${resposta}`);
//       finalizarAguardar();
//     });

//   return false;
// }
