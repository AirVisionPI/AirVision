function btnEntrar() {
  var formulario = new URLSearchParams(
    new FormData(document.getElementById("form_login"))
  );
  var email = formulario.get("email");
  var senha = formulario.get("senha");

  try {
    const myBD = JSON.parse(localStorage.getItem("myBD"));

    const verificacaoLogin = myBD.find(
      (registro) => registro.email === email && registro.senha === senha
    );

    if (verificacaoLogin) {
      alert(`Bem Vindo ${verificacaoLogin.nome}`);
      sessionStorage.email = verificacaoLogin.email;
      sessionStorage.cargo = verificacaoLogin.cargo;
      sessionStorage.nome = verificacaoLogin.nome;

      window.location = "./Dashboard/monitoramento.html";
    } else {
      alert(`Cadastro Inexistente`);
    }
  } catch (error) {
    console.error(error);
    alert(`Cadastro Inexistente`);
  }
}
