create database airvision ;

use airvision;

CREATE TABLE companhia_aerea (
Id_aeroporto int primary key auto_increment,
razao_aeroporto varchar (45),
cnpj_aeroporto char (14),
responsavel_aeroporto varchar(45),
localidade_aeroporto varchar(45)
);

CREATE TABLE usuario(
Id_usuario int primary key auto_increment,
nome_usuario varchar(45),
email_usuario varchar(40),
 senha_usuario varchar(20),
cargo_usuario varchar(45),
fk_aeroporto int,
fk_cargo varchar(45) 
FOREIGN KEY (fk_cargo) REFERENCES usuario (Id_usuario)
 FOREIGN KEY (fk_aeroporto) REFERENCES companhia aerea (Id_aeroporto)
);

create table maquina (
id_maquina int primary key auto_increment,
nome_maquina varchar (45),
fk_aeroporto int
FOREIGN KEY (fk_aeroporto) REFERENCES companhia aerea (Id_aeroporto)
);



CREATE TABLE status_maquinas (
id_status INT PRIMARY KEY AUTO_INCREMENT,
 status_maquinas CHAR(30),
fk_maquina INT,
FOREIGN KEY (fk_maquina) REFERENCES maquina (id_maquina)
);



create table disco (
id_disco int primary key auto_increment,
especificacao varchar(45) ,
fk_maquina_disco int,
foreign key (fk_maquina_disco)references maquina (id_maquina)
);



create table memoria (
id_memoria int primary key auto_increment,
especificacao varchar(45) ,
fk_maquina_memoria int,
foreign key (fk_maquina_memoria)references maquina (id_maquina)
);



create table cpu (
id_cpu int primary key auto_increment,
especificacao varchar(45) ,
fk_maquina_cpu int,
foreign key (fk_maquina_cpu)references maquina (id_maquina)
);


create table logs_disco(
    id_logs_disco int primary key auto_increment,
    logs varchar(100)
    data_hora datetime 
)


CREATE TABLE dados_agua(
Idagua int primary key auto_increment,
Consumo VARCHAR(50),
Dtconsumo DATETIME DEFAULT current_timestamp,
Fkestufa INT,
FOREIGN KEY (Fkestufa) REFERENCES Estufa (idestufa)
);







-- SCRIPT PARA SQL SERVER AZURE
CREATE TABLE companhia_aerea (
Id_aeroporto int primary key identity,
razao_aeroporto varchar (45),
cnpj_aeroporto char (14),
responsavel_aeroporto varchar(45),
localidade_aeroporto varchar(45)
);

CREATE TABLE usuario(
Id_usuario int primary key identity,
nome_usuario varchar(45),
email_usuario varchar(40),
 senha_usuario varchar(20),
cargo_usuario varchar(45),
fk_aeroporto int,
fk_cargo varchar(45) 
FOREIGN KEY (fk_cargo) REFERENCES usuario (Id_usuario)
 FOREIGN KEY (fk_aeroporto) REFERENCES companhia aerea (Id_aeroporto)
);

create table maquina (
id_maquina int primary key identity,
nome_maquina varchar (45),
fk_aeroporto int
FOREIGN KEY (fk_aeroporto) REFERENCES companhia aerea (Id_aeroporto)
);



CREATE TABLE status_maquinas (
id_status INT PRIMARY KEY identity,
 status_maquinas CHAR(30),
fk_maquina INT,
FOREIGN KEY (fk_maquina) REFERENCES maquina (id_maquina)
);



create table disco (
id_disco int primary key identity,
especificacao varchar(45) ,
fk_maquina_disco int,
foreign key (fk_maquina_disco)references maquina (id_maquina)
);



create table memoria (
id_memoria int primary key identity,
especificacao varchar(45) ,
fk_maquina_memoria int,
foreign key (fk_maquina_memoria)references maquina (id_maquina)
);



create table cpu (
id_cpu int primary key identity,
especificacao varchar(45) ,
fk_maquina_cpu int,
foreign key (fk_maquina_cpu)references maquina (id_maquina)
);


create table logs_disco(
    id_logs_disco int primary key identity,
    logs varchar(100)
    data_hora datetime 
)
