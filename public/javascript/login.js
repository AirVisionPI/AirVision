  function btnEntrar() {

    var formulario = new URLSearchParams(new FormData(document.getElementById("form_login")));
    var email = formulario.get("email");
    var senha = formulario.get("senha");

    sessionStorage.email = localStorage.getItem("myBD");
    sessionStorage.senha = senha;
    sessionStorage.cargo = 
    


  }