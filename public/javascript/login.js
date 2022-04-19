function btnEntrar() {

  var formulario = new URLSearchParams(new FormData(document.getElementById("form_login")));
  var email = formulario.get("email");
  var senha = formulario.get("senha");

  const myBD = JSON.parse(localStorage.getItem("myBD"));

  const verificacaoLogin = myBD.find(registro => registro.email === email && registro.senha === senha);

  if(verificacaoLogin) {
    alert(`Bem Vindo ${verificacaoLogin.nome}`);
    sessionStorage.email = verificacaoLogin.email;
    sessionStorage.cargo = verificacaoLogin.cargo;
    window.location = "./Dashboard/monitoramento.html";
  } else {
    alert(`Cadastro Inexistente`);
  }
}