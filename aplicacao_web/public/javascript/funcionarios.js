// =========================================
// -------------DADOS DO BANCO--------------
// =========================================

// AQUI VAI FAZER FETCH DO BANCO MEU AMIGO..

function requestEmployes() {
  var fk_aeroporto = sessionStorage.idAeroporto;

  fetch(`/funcionarios/table/${fk_aeroporto}`)
    .then(function (response) {
      if (response.ok) {
        response.json().then(function (resposta) {
          console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);
          atualizarPainelFuncionarios(resposta);
        });
      } else {
        console.error("Nenhum dado encontrado ou erro na API");
      }
    })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados: ${error.message}`);
    });
}

// =========================================
// --------------FUNÇÃO ONLOAD--------------
// =========================================
function atualizarPainelFuncionarios(resposta) {
  resposta.forEach(
    (
      { id_usuario, nome_usuario, email_usuario, senha_usuario, cargo_usuario },
      index
    ) => {
      var statusClassVerSenha = "fa-solid fa-eye-slash";

      var verSenha = false;

      if (verSenha == true) {
        statusClassVerSenha = "fa-solid fa-eye";
      }
      tbody_painelfunc.innerHTML += `
       <tr class="tr">
       <!--NOME DO TOTEM--->
       <td id="id_usuario_func_${index}">${id_usuario}</td>

       <!--NOME DO TOTEM--->
       <td id="nome_usuario_func_${index}">${nome_usuario}</td>

       <!--NOME DO TOTEM--->
       <td id="email_usuario_func_${index}">${email_usuario}</td>

       <!--NOME DO TOTEM--->
       <td id="senha_usuario_func_${index}">${senha_usuario} <i class="fa-solid fa-eye-slash"></i></td>

       <!--NOME DO TOTEM--->
       <td id="cargo_usuario_func_${index}">${cargo_usuario}</td>
       </tr>
      `;
    }
  );

  // document
  //   .querySelectorAll(".chart")
  //   .forEach((td) =>
  //     td.addEventListener("mouseover", () => console.log("HOVERR"))
  //   );
}
