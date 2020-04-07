drop database bth;

create database bth;

use bth;

create table tblColegios(
	id int primary key auto_increment,
	nombre varchar(50) not null,
	sigep varchar(10) not null,
	director varchar(65),
	direccion varchar(100),
	telefono varchar(8),
	esModulo bool,
	correo varchar(50) not null,
	username varchar(50) not null,
	contrasenia text not null,
	estado bool
);

create table tblEstudiantes(
	id int primary key auto_increment,
	ci varchar(9),
	nombre varchar(50) not null,
	aPaterno varchar(30) not null,
	aMaterno varchar(30),
	contrasenia varchar(50),
	telefono varchar(8),
	genero varchar(1) not null,
	/*genero m:masculino, f:femenino*/
	nacimiento date not null,
	estado varchar(1),
	/*Estado: a:Abandono, g:Graduado ,q:5, s:6*/
	idColegio int,
	foreign key (idColegio) references tblColegios(id)
);

create table tblDocentes(
	id int primary key auto_increment,
	nombre varchar(50) not null,
	aPaterno varchar(30) not null,
	aMaterno varchar(30),
	correo varchar(50) not null,
	username varchar(50) not null,
	contrasenia text not null,
	estado bool
);

create table tblCarreras(
	id int primary key auto_increment,
	nombre varchar(50) not null,
	sigla varchar(5) not null,
	descripcion varchar(200),
	img text
);

create table tblInformaciones(
	id int primary key auto_increment,
	titulo varchar(50) not null,
	descripcion text not null,
	img text not null,
	idCarrera int not null,
	foreign key (idCarrera) references tblCarreras(id)
);

create table tblGestiones(
	id int primary key auto_increment,
	mesAPagar int not null
)
AUTO_INCREMENT = 20;

create table tblGrupos(
	id int primary key auto_increment,
	nivel varchar(1) not null,
	/*nivel: q:5, s:6*/
	estado varchar(1),
	/*estado a:aprovado, r:reprobado, e:Ejecutando*/
	idGestion int not null,
	/*gestion: 20:2020 - 38: 2038 */
	idColegio int not null,
	idCarrera int not null,
	idDocente int,
	foreign key (idGestion) references tblGestiones(id),
	foreign key (idColegio) references tblColegios(id),
	foreign key (idCarrera) references tblCarreras(id),
	foreign key (idDocente) references tblDocentes(id)
);

create table tblHoras(
	id int primary key auto_increment,
	inicio time not null,
	fin time not null
);

create table tblDias(
	id int primary key auto_increment,
	dia int not null,
	idGrupo int not null,
	idHora int not null,
	foreign key (idGrupo) references tblGrupos(id),
	foreign key (idHora) references tblHoras(id)	
);

create table tblProyectos(
	id int primary key auto_increment,
	nombre varchar(50) not null,
	descripcion text not null,
	imgJson text,
	idCarrera int not null,
	foreign key (idCarrera) references tblCarreras(id)
);

create table tblParticipantes(
	id int primary key auto_increment,
	idEstudiante int not null,
	idProyecto int not null,
	foreign key (idEstudiante) references tblEstudiantes(id),
	foreign key (idProyecto) references tblProyectos(id)
);

create table tblEstudiantes_grupos(
	id int primary key auto_increment,
	idEstudiante int not null,
	idGrupo int not null,
	foreign key (idEstudiante) references tblEstudiantes(id),
	foreign key (idGrupo) references tblGrupos(id)
);

create table tblPagos(
	id int primary key auto_increment,
	monto decimal(5,2) not null,
	mes int not null,
	/*mes 1: enero - 12:diciembre*/
	fecha date not null,
	idEstudiantes_grupos int not null,
	foreign key (idEstudiantes_grupos) references tblEstudiantes_grupos(id)
);

create table tblAsistencias(
	id int primary key auto_increment,
	tipo varchar(1) not null,
	/*Tipo:  r:Retrasos, l:Licencia, f:Faltas*/
	motivo varchar(100),
	fecha date not null,
	trimestre varchar(1),
	idEstudiantes_grupos int not null,
	foreign key (idEstudiantes_grupos) references tblEstudiantes_grupos(id)
);

create table tblParametros(
	id int primary key auto_increment,
	nombre varchar(30) not null,
	tipo varchar(1) not null,
	/*tipo s:Saver, h:Hacer, d:Decidir*/
	trimestre varchar(1) not null
	/*trimestre 1:primero, 2:Segundo, 3:tercero*/
);

create table tblNotas(
	id int primary key auto_increment,
	nota int not null,
	idEstudiantes_grupos int not null,
	idParametro int not null,
	foreign key (idEstudiantes_grupos) references tblEstudiantes_grupos(id),
	foreign key (idParametro) references tblParametros(id)
);

create table tblTokens(
	id int primary key auto_increment,
	sha text not null,
	email varchar(50) not null,
	tipo varchar(5) not null,
	/*cc*/
	usuario varchar(1) not null,
	/*tipo: d:docente, c:colegio, a:admin*/
	fecha date not null
);
