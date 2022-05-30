var express = require("express");
var router = express.Router();

var employesController = require("../controllers/employesController");

// ESTA ROTA CHAMA FUNCIONARIOS QUANDO O ADMIN DO AEROPOROTO ESTIVER LOGADO.
router.get("/table/:fk_aeroporto", function (req, res) {
  employesController.buscarFuncionarios(req, res);
});

router.get("/table/gestor/:fk_aeroporto", function (req, res) {
  employesController.buscarFuncionariosGestor(req, res);
});

module.exports = router;
