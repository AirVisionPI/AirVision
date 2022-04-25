USE airvision;

CREATE TABLE companhia_aerea (
	id_aeroporto INT PRIMARY KEY AUTO_INCREMENT,
	razao_aeroporto VARCHAR(45),
	cnpj_aeroporto CHAR(14),
	responsavel_aeroporto VARCHAR(45),
	localidade_aeroporto VARCHAR(45)
);

CREATE TABLE usuario (
	id_usuario INT PRIMARY KEY AUTO_INCREMENT,
	nome_usuario VARCHAR(45),
    email_usuario VARCHAR(45),
	senha_usuario CHAR(13),
	cargo_usuario VARCHAR(45),
	fk_aeroporto INT,
	FOREIGN KEY (fk_aeroporto) REFERENCES companhia_aerea(id_aeroporto)
    
); 

CREATE TABLE maquinas(
    id_toten INT PRIMARY KEY AUTO_INCREMENT,
    nome_maquina VARCHAR(45),
    hostname VARCHAR(45),
    sistema_operacional VARCHAR(45),
    arquitetura INT,
    fk_aeroporto INT,
    FOREIGN KEY (fk_aeroporto) REFERENCES companhia_aerea(id_aeroporto)
);
CREATE TABLE status_maquinas(
id_status INT PRIMARY KEY AUTO_INCREMENT,
status_maquinas VARCHAR(45),
fk_maquina INT,
 FOREIGN KEY (fk_maquina) REFERENCES maquinas(id_toten)
);
CREATE TABLE disco(
    id_disco INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    modelo VARCHAR(45),
    leitura BIT,
    escrita BIT,
    tamanho_atual_fila VARCHAR(45),
    fk_disco_id_toten INT,
    FOREIGN KEY (fk_disco_id_toten) REFERENCES maquinas(id_toten)

);
CREATE TABLE memoria(
    id_memoria INT PRIMARY KEY AUTO_INCREMENT,
    total VARCHAR (45),
    uso VARCHAR(45),
    fk_memoria_id_toten INT,
    FOREIGN KEY (fk_memoria_id_toten) REFERENCES maquinas(id_toten)

);
CREATE TABLE cpu(
    id_cpu INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    uso VARCHAR(45),
 
fk_cpu_id_toten INT,
FOREIGN KEY (fk_cpu_id_toten) REFERENCES maquinas(id_toten)
);

CREATE TABLE logs_disco(
    id_logs_disco INT PRIMARY KEY AUTO_INCREMENT,
    emuso_leitura BIT,
    emuso_escrita BIT,
    tamanho_atual_fila VARCHAR(45),
    data_hora DATETIME,
    fk_logs_id_disco INT,
    FOREIGN KEY (fk_logs_id_disco) REFERENCES disco(id_disco)
);
CREATE TABLE logs_memoria(
    id_logs_memoria INT PRIMARY KEY AUTO_INCREMENT,
    em_uso VARCHAR(45),
    data_hora DATETIME,
    fk_logs_id_memoria INT,
FOREIGN KEY (fk_logs_id_memoria) REFERENCES memoria(id_memoria)
);
CREATE TABLE logs_cpu(
    id_logs_cpu INT PRIMARY KEY AUTO_INCREMENT,
    em_uso VARCHAR(3),
    data_hora DATETIME,
    fk_logs_id_cpu INT,
    FOREIGN KEY (fk_logs_id_cpu) REFERENCES cpu(id_cpu)
);