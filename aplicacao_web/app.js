process.env.AMBIENTE_PROCESSO = "producao";
// process.env.AMBIENTE_PROCESSO = "desenvolvimento";

var express = require("express");
var cors = require("cors");
var path = require("path");
var PORTA = 8080;

var app = express();

var indexRouter = require("./src/routes/index");
var usuarioRouter = require("./src/routes/usuarios");
var funcionariosRouter = require("./src/routes/funcionarios");
var logsRouter = require("./src/routes/logs");

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, "public")));

app.use(cors());

app.use("/", indexRouter);
app.use("/usuarios", usuarioRouter);
app.use("/funcionarios", funcionariosRouter);
app.use("/logs", logsRouter);


app.listen(PORTA, function() {
    console.log(`Servidor Aberto! Porta ${PORTA}`);
});