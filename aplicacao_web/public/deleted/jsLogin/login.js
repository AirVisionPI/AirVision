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

function btnEntrar() {
  var formulario = new URLSearchParams(
    new FormData(document.getElementById("form_login"))
  );

  var email = formulario.get("email");
  var senha = formulario.get("senha");

  console.log("FORM LOGIN: ", email);
  console.log("FORM SENHA: ", senha);
}

// ===================================================================================== //
//                                  CADASTRO DO USUÁRIO                                     //
// ===================================================================================== //
