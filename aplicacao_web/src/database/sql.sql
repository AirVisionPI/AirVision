CREATE DATABASE airvision;

USE airvision;

CREATE TABLE companhia_aerea (
    id_aeroporto INT PRIMARY KEY auto_increment,
    razao_aeroporto VARCHAR(150),
    cnpj_aeroporto CHAR (15),
    responsavel_aeroporto VARCHAR(150),
    localidade_aeroporto VARCHAR(150)
);

CREATE TABLE usuario(
    id_usuario INT PRIMARY KEY auto_increment,
    nome_usuario VARCHAR(150),
    email_usuario VARCHAR(150),
    senha_usuario VARCHAR(150),
    cargo_usuario VARCHAR(150),
    fk_aeroporto INT,
    FOREIGN KEY (fk_aeroporto) REFERENCES companhia_aerea (id_aeroporto)
);

CREATE TABLE maquina (
    id_maquina INT PRIMARY KEY auto_increment,
    hostname VARCHAR(150),
    sistema_operacional VARCHAR(150),
    fk_aeroporto INT,
    FOREIGN KEY (fk_aeroporto) REFERENCES companhia_aerea (id_aeroporto)
);

CREATE TABLE status_maquina (
    id_status INT PRIMARY KEY AUTO_INCREMENT,
    status_maquina VARCHAR(150),
    fk_maquina INT,
    FOREIGN KEY (fk_maquina) REFERENCES maquina (id_maquina)
);

CREATE TABLE disco (
    id_disco INT PRIMARY KEY auto_increment,
    nome VARCHAR(150),
    modelo VARCHAR(150),
    fk_maquina INT,
    FOREIGN KEY (fk_maquina) REFERENCES maquina (id_maquina)
);

CREATE TABLE memoria (
    id_memoria INT PRIMARY KEY auto_increment,
    total VARCHAR(150),
    fk_maquina INT,
    FOREIGN KEY (fk_maquina) REFERENCES maquina (id_maquina)
);

CREATE TABLE cpu (
    id_cpu INT PRIMARY KEY auto_increment,
    nome_processador VARCHAR(150),
    identificador VARCHAR(150),
    fabricante VARCHAR(150),
    fk_maquina INT,
    FOREIGN KEY (fk_maquina) REFERENCES maquina (id_maquina)
);

CREATE TABLE logs_disco(
    id_logs_disco INT PRIMARY KEY auto_increment,
    disco_leitura VARCHAR(150),
    disco_escrita VARCHAR(150),
    tamanho_atual_fila VARCHAR(150),
    data_hora datetime,
    fk_disco INT,
    FOREIGN KEY (fk_disco) REFERENCES disco (id_disco)
);

CREATE TABLE logs_cpu(
    id_logs_cpu INT PRIMARY KEY auto_increment,
    em_uso VARCHAR(150),
    data_hora datetime,
    fk_cpu INT,
    FOREIGN KEY (fk_cpu) REFERENCES cpu (id_cpu)
);

CREATE TABLE logs_memoria(
    id_logs_memoria INT PRIMARY KEY auto_increment,
    ram_disponivel VARCHAR(150),
    ram_uso VARCHAR(150),
    data_hora datetime,
    fk_memoria INT,
    FOREIGN KEY (fk_memoria) REFERENCES memoria (id_memoria)
);