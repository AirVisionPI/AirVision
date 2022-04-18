  function btnCadastrar() {

    let formulario = new URLSearchParams(new FormData(document.getElementById("form_cadastro")));
    let nome = formulario.get("nome");
    let email = formulario.get("email");
    let senha = formulario.get("senha");
    let razao_social = formulario.get("razao_social");
    let cnpj = formulario.get("cnpj");
    let local_companhia = formulario.get("local_companhia");

    const myBD = JSON.parse(localStorage.getItem("myBD"));

    if(myBD){
      localStorage.setItem("myBD", JSON.stringify([...myBD,{
        id: myBD.at(-1).id + 1,
        nome, 
        email, 
        senha, 
        razao_social, 
        cnpj, 
        local_companhia,
        cargo: "admin"
      }]))
    }else{
      localStorage.setItem("myBD", JSON.stringify([{
        id: 1, 
        nome, 
        email, 
        senha, 
        razao_social, 
        cnpj, 
        local_companhia, 
        cargo: "admin"
      }]))
    }
    
  }