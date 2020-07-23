
-- Tabla Usuarios
CREATE TABLE tblUsuarios(
	id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apPaterno VARCHAR(50) NOT NULL,
    apMaterno VARCHAR(50) NOT NULL,
	correo VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
    password TEXT,
    estado INT,
    PRIMARY KEY(id)
);

insert into tblusuarios values(default,"Admin","Admin","","admin@gmail.com","admin","16DC65E18E123DA36C11466AACF3965A",1);

create table tblUsuariosRoles(
	id INT NOT NULL AUTO_INCREMENT,
	idreference INT,
	idUsuario INT NOT NULL,
	idRol INT NOT NULL,
	estado BOOLEAN,
    PRIMARY KEY(id)
);

-- Tabla Roles
CREATE TABLE tblRoles(
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(25) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE tblPermisos(
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tblRolesPermisos(
	idRol INT,
    idPermiso INT,
    FOREIGN KEY (idRol) REFERENCES tblRoles(id),
    FOREIGN KEY (idPermiso) REFERENCES tblPermisos(id)
);

INSERT INTO tblRoles VALUES(DEFAULT, 'Docente');
INSERT INTO tblRoles VALUES(DEFAULT, 'Administrador');
INSERT INTO tblRoles VALUES(DEFAULT, 'AdministradorBTH');

-- 1 AdministradorRoles --
INSERT INTO tblPermisos VALUES(DEFAULT, 'AdministradorRoles', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'AdministradorRoles', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'AdministradorRoles', 'EscrituraLectura');

-- 2 ColegiosR --
INSERT INTO tblPermisos VALUES(DEFAULT, 'ColegiosR', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'ColegiosR', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'ColegiosR', 'EscrituraLectura');

-- 3 Gestiones --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Gestiones', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Gestiones', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Gestiones', 'EscrituraLectura');

-- 4 Paginas Inicio --
INSERT INTO tblPermisos VALUES(DEFAULT, 'PaginaInicio', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'PaginaInicio', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'PaginaInicio', 'EscrituraLectura');

-- 5 Perfil Carrera --
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilCarrera', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilCarrera', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilCarrera', 'EscrituraLectura');

-- 6 Perfil Colegio --
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilColegio', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilColegio', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilColegio', 'EscrituraLectura');

-- 7 Perfil Estudiante --
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilEstudiante', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilEstudiante', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'PerfilEstudiante', 'EscrituraLectura');

-- 8 Reportes Notas --
INSERT INTO tblPermisos VALUES(DEFAULT, 'ReportesNotas', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'ReportesNotas', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'ReportesNotas', 'EscrituraLectura');

-- 9 Reportes Pagos --
INSERT INTO tblPermisos VALUES(DEFAULT, 'ReportesPagos', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'ReportesPagos', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'ReportesPagos', 'EscrituraLectura');

-- 10 Administrador Proyectos --
INSERT INTO tblPermisos VALUES(DEFAULT, 'AdminProyecto', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'AdminProyecto', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'AdminProyecto', 'EscrituraLectura');

-- 11 Asistencia Docente --
INSERT INTO tblPermisos VALUES(DEFAULT, 'AsistenciaDocente', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'AsistenciaDocente', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'AsistenciaDocente', 'EscrituraLectura');

-- 12 Base --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Base', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Base', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Base', 'EscrituraLectura');

-- 13 Carrera --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Carrera', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Carrera', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Carrera', 'EscrituraLectura');

-- 14 Change Password
INSERT INTO tblPermisos VALUES(DEFAULT, 'ChangePassword', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'ChangePassword', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'ChangePassword', 'EscrituraLectura');

-- 15 Colegios --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Colegios', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Colegios', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Colegios', 'EscrituraLectura');

-- 16 Docente Grupos
INSERT INTO tblPermisos VALUES(DEFAULT, 'DocenteGrupos', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'DocenteGrupos', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'DocenteGrupos', 'EscrituraLectura');

-- 17 EmailSendCorreo --
INSERT INTO tblPermisos VALUES(DEFAULT, 'EmailSendCorreo', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'EmailSendCorreo', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'EmailSendCorreo', 'EscrituraLectura');

-- 18 Grupos --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Grupos', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Grupos', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Grupos', 'EscrituraLectura');

-- 19 Index --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Index', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Index', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Index', 'EscrituraLectura');

-- 20 Mensualidades docente
INSERT INTO tblPermisos VALUES(DEFAULT, 'MensualidadDocentes', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'MensualidadDocentes', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'MensualidadDocentes', 'EscrituraLectura');

-- 21 Nota --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Nota', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Nota', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Nota', 'EscrituraLectura');

-- 22 Pagos --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Pagos', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Pagos', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Pagos', 'EscrituraLectura');

-- 23 Proyectos --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Proyectos', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Proyectos', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Proyectos', 'EscrituraLectura');

-- 24 REstudiantes --
INSERT INTO tblPermisos VALUES(DEFAULT, 'REstudiantes', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'REstudiantes', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'REstudiantes', 'EscrituraLectura');

-- 25 Registro --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Registro', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Registro', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Registro', 'EscrituraLectura');

-- 26 Registro Colegio --
INSERT INTO tblPermisos VALUES(DEFAULT, 'RegistroColegio', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'RegistroColegio', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'RegistroColegio', 'EscrituraLectura');

-- 27 Registro Docente --
INSERT INTO tblPermisos VALUES(DEFAULT, 'RegistroDocente', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'RegistroDocente', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'RegistroDocente', 'EscrituraLectura');

-- 28 Verificar --
INSERT INTO tblPermisos VALUES(DEFAULT, 'Verificar', 'SoloEscritura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Verificar', 'SoloLectura');
INSERT INTO tblPermisos VALUES(DEFAULT, 'Verificar', 'EscrituraLectura');
