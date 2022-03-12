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

$("#pwd-r, #cpwd-r").on("keyup", function () {
  if ($("#pwd-r").val() == $("#cpwd-r").val()) {
    $("#cpwd-r").css("border-color", "green");
    $("#cpwd-r").css("color", "green");
  } else {
    $("#cpwd-r").css("border-color", "red");
    $("#cpwd-r").css("color", "red");
  }
});

// ===================================================================================== //
//                                  LOGIN DO USUÁRIO                                     //
// ===================================================================================== //
