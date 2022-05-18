CREATE TABLE companhia_aerea (
id_aeroporto int primary key auto_increment,
razao_aeroporto varchar(150),
cnpj_aeroporto char (15),
responsavel_aeroporto varchar(150),
localidade_aeroporto varchar(150)
);

CREATE TABLE usuario(
id_usuario int primary key auto_increment,
nome_usuario varchar(150),
email_usuario varchar(150),
senha_usuario varchar(150),
cargo_usuario varchar(150),
fk_aeroporto int,
 FOREIGN KEY (fk_aeroporto) REFERENCES companhia_aerea (id_aeroporto)
);

create table maquina (
id_maquina int primary key auto_increment,
hostname varchar(150),
sistema_operacional varchar(150),
fk_aeroporto int,
FOREIGN KEY (fk_aeroporto) REFERENCES companhia_aerea (id_aeroporto)
);

CREATE TABLE status_maquina (
id_status INT PRIMARY KEY AUTO_INCREMENT,
status_maquina varchar(150),
fk_maquina INT,
FOREIGN KEY (fk_maquina) REFERENCES maquina (id_maquina)
);

create table disco (
id_disco int primary key auto_increment,
nome varchar(150) ,
modelo varchar(150) ,
fk_maquina int,
foreign key (fk_maquina)references maquina (id_maquina)
);

create table memoria (
id_memoria int primary key auto_increment,
total varchar(150) ,
fk_maquina int,
foreign key (fk_maquina)references maquina (id_maquina)
);

create table cpu (
id_cpu int primary key auto_increment,
nome_processador varchar(150) ,
identificador varchar(150) ,
fabricante varchar(150) ,
fk_maquina int,
foreign key (fk_maquina)references maquina (id_maquina)
);

create table logs_disco(
    id_logs_disco int primary key auto_increment,
    disco_leitura varchar(150) ,
    disco_escrita varchar(150) ,
    tamanho_atual_fila varchar(150) ,
    data_hora datetime ,
    fk_disco int,
foreign key (fk_disco)references disco (id_disco)
);

create table logs_cpu(
    id_logs_cpu int primary key auto_increment,
    em_uso varchar(150) ,
    data_hora datetime ,
    fk_cpu int,
foreign key (fk_cpu)references cpu (id_cpu)
);

create table logs_memoria(
    id_logs_memoria int primary key auto_increment,
    ram_disponivel varchar(150) ,
    ram_uso varchar(150) ,
    data_hora datetime ,
    fk_memoria int,
foreign key (fk_memoria)references memoria (id_memoria)
);