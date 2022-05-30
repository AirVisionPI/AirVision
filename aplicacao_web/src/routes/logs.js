var express = require("express");
var router = express.Router();

var logsController = require("../controllers/logsController");

// ESTA ROTA CHAMA FUNCIONARIOS QUANDO O ADMIN DO AEROPOROTO ESTIVER LOGADO.
router.get("/table/:fk_aeroporto", function (req, res) {
  logsController.buscarLogs(req, res);
});

router.get("/painel/:id_maquina", function (req, res) {
  logsController.buscarLogsPainel(req, res);
});

module.exports = router;