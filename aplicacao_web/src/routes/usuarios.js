var express = require("express");
var router = express.Router();

var usuarioController = require("../controllers/usuarioController");

router.get("/", function(req, res) {
    usuarioController.testar(req, res);
});

router.post("/cadastrar", function(req, res) {
    usuarioController.cadastrar(req, res);
})

router.post("/cadastrarUser", function(req, res) {
    usuarioController.cadastrarUser(req, res)
})

router.post("/cadastrarFuncionario", function(req, res) {
    usuarioController.cadastrarFuncionario(req, res);
})

router.post("/autenticar", function(req, res) {
    usuarioController.entrar(req, res);
});

module.exports = router;