-- Tabla Usuarios
CREATE TABLE tblusuarios(
	id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apPaterno VARCHAR(50) NOT NULL,
    apMaterno VARCHAR(50) NOT NULL,
    password TEXT,
    estado INT,
    PRIMARY KEY(id)
);

-- Tabla Roles
CREATE TABLE tblroles(
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(25) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES tblusuarios(id)
);

INSERT INTO tblroles VALUES(DEFAULT, 'Estudiante');
INSERT INTO tblroles VALUES(DEFAULT, 'Docente');
INSERT INTO tblroles VALUES(DEFAULT, 'Administrador');
INSERT INTO tblroles VALUES(DEFAULT, 'AdministradorBTH');

CREATE TABLE tblpermisos(
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE tblrolespermisos(
	idRol INT,
    idPermiso INT,
    FOREIGN KEY (idRol) REFERENCES tblroles(id),
    FOREIGN KEY (idPermiso) REFERENCES tblPermisos(id)
);

CREATE TABLE tblusuarioroles(
	idUsuario INT,
    idRol INT,
    FOREIGN KEY (idUsuario) REFERENCES tblusuarios(id),
    FOREIGN KEY (idRol) REFERENCES tblroles(id)
);


-- Usuario
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertUsuario');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getUsuario');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateUsuario');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteUsuario');

-- Carrera
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertCarrera');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getCarrera');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateCarrera');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteCarrera');

-- Asistencia 
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertAsistencia');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getAsistencia');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateAsistencia');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteAsistencia');

-- Colegio
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertColegio');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getColegio');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateColegio');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteColegio');

-- Docente
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertDocente');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getDocente');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateDocente');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteDocente');

-- Estudiante
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertEstudiante');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getEstudiante');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateEstudiante');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteEstudiante');

-- Gestion
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertGestion');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getGestion');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateGestion');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteGestion');

-- Grupos
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertGrupos');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getGrupos');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateGrupos');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteGrupos');

-- Notas
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertNotas');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getNotas');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateNotas');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteNotas');

-- Pagos
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertPagos');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getPagos');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updatePagos');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deletePagos');

-- Proyecto
INSERT INTO tblpermisos VALUES(DEFAULT, 'insertProyecto');
INSERT INTO tblpermisos VALUES(DEFAULT, 'getProyecto');
INSERT INTO tblpermisos VALUES(DEFAULT, 'updateProyecto');
INSERT INTO tblpermisos VALUES(DEFAULT, 'deleteProyecto');

-- Permisos de estudiantes
INSERT INTO tblrolesspermisos VALUES(1, 2);
INSERT INTO tblrolesspermisos VALUES(1, 3);
INSERT INTO tblrolesspermisos VALUES(1, 6);
INSERT INTO tblrolesspermisos VALUES(1, 10);
INSERT INTO tblrolesspermisos VALUES(1, 14);
INSERT INTO tblrolesspermisos VALUES(1, 18);
INSERT INTO tblrolesspermisos VALUES(1, 21);
INSERT INTO tblrolesspermisos VALUES(1, 22);
INSERT INTO tblrolesspermisos VALUES(1, 23);
INSERT INTO tblrolesspermisos VALUES(1, 24);
INSERT INTO tblrolesspermisos VALUES(1, 26);
INSERT INTO tblrolesspermisos VALUES(1, 30);
INSERT INTO tblrolesspermisos VALUES(1, 34);
INSERT INTO tblrolesspermisos VALUES(1, 38);
INSERT INTO tblrolesspermisos VALUES(1, 42);
INSERT INTO tblrolesspermisos VALUES(1, 46);

SELECT * FROM tblpermisos;
-- Permisos para docentes
INSERT INTO tblrolesspermisos VALUES(2, 2);
INSERT INTO tblrolesspermisos VALUES(2, 3);
INSERT INTO tblrolesspermisos VALUES(2, 4);
INSERT INTO tblrolesspermisos VALUES(2, 5);
INSERT INTO tblrolesspermisos VALUES(2, 9);
INSERT INTO tblrolesspermisos VALUES(2, 10);
INSERT INTO tblrolesspermisos VALUES(2, 11);
INSERT INTO tblrolesspermisos VALUES(2, 12);
INSERT INTO tblrolesspermisos VALUES(2, 14);
INSERT INTO tblrolesspermisos VALUES(2, 17);
INSERT INTO tblrolesspermisos VALUES(2, 19);
INSERT INTO tblrolesspermisos VALUES(2, 22);
INSERT INTO tblrolesspermisos VALUES(2, 29);
INSERT INTO tblrolesspermisos VALUES(2, 30);
INSERT INTO tblrolesspermisos VALUES(2, 31);
INSERT INTO tblrolesspermisos VALUES(2, 32);
INSERT INTO tblrolesspermisos VALUES(2, 33);
INSERT INTO tblrolesspermisos VALUES(2, 34);
INSERT INTO tblrolesspermisos VALUES(2, 35);
INSERT INTO tblrolesspermisos VALUES(2, 41);
INSERT INTO tblrolesspermisos VALUES(2, 42);
INSERT INTO tblrolesspermisos VALUES(2, 47);

select * from tblpermisos;
-- Roles administrador
INSERT INTO tblrolesspermisos VALUES(3, 2);
INSERT INTO tblrolesspermisos VALUES(3, 3);
INSERT INTO tblrolesspermisos VALUES(3, 4);
INSERT INTO tblrolesspermisos VALUES(3, 5);
INSERT INTO tblrolesspermisos VALUES(3, 6);
INSERT INTO tblrolesspermisos VALUES(3, 7);
INSERT INTO tblrolesspermisos VALUES(3, 8);
INSERT INTO tblrolesspermisos VALUES(3, 12);
INSERT INTO tblrolesspermisos VALUES(3, 13);
INSERT INTO tblrolesspermisos VALUES(3, 14);
INSERT INTO tblrolesspermisos VALUES(3, 15);
INSERT INTO tblrolesspermisos VALUES(3, 16);
INSERT INTO tblrolesspermisos VALUES(3, 17);
INSERT INTO tblrolesspermisos VALUES(3, 18);
INSERT INTO tblrolesspermisos VALUES(3, 19);
INSERT INTO tblrolesspermisos VALUES(3, 20);
INSERT INTO tblrolesspermisos VALUES(3, 21);
INSERT INTO tblrolesspermisos VALUES(3, 22);
INSERT INTO tblrolesspermisos VALUES(3, 23);
INSERT INTO tblrolesspermisos VALUES(3, 24);
INSERT INTO tblrolesspermisos VALUES(3, 25);
INSERT INTO tblrolesspermisos VALUES(3, 26);
INSERT INTO tblrolesspermisos VALUES(3, 27);
INSERT INTO tblrolesspermisos VALUES(3, 28);
INSERT INTO tblrolesspermisos VALUES(3, 32);
INSERT INTO tblrolesspermisos VALUES(3, 36);
INSERT INTO tblrolesspermisos VALUES(3, 37);
INSERT INTO tblrolesspermisos VALUES(3, 38);
INSERT INTO tblrolesspermisos VALUES(3, 39);
INSERT INTO tblrolesspermisos VALUES(3, 40);
INSERT INTO tblrolesspermisos VALUES(3, 44);

-- Permisos Administrador BTH
INSERT INTO tblrolesspermisos VALUES(4, 1);
INSERT INTO tblrolesspermisos VALUES(4, 2);
INSERT INTO tblrolesspermisos VALUES(4, 3);
INSERT INTO tblrolesspermisos VALUES(4, 4);
INSERT INTO tblrolesspermisos VALUES(4, 5);
INSERT INTO tblrolesspermisos VALUES(4, 6);
INSERT INTO tblrolesspermisos VALUES(4, 7);
INSERT INTO tblrolesspermisos VALUES(4, 8);
INSERT INTO tblrolesspermisos VALUES(4, 9);
INSERT INTO tblrolesspermisos VALUES(4, 10);
INSERT INTO tblrolesspermisos VALUES(4, 11);
INSERT INTO tblrolesspermisos VALUES(4, 12);
INSERT INTO tblrolesspermisos VALUES(4, 13);
INSERT INTO tblrolesspermisos VALUES(4, 14);
INSERT INTO tblrolesspermisos VALUES(4, 15);
INSERT INTO tblrolesspermisos VALUES(4, 16);
INSERT INTO tblrolesspermisos VALUES(4, 17);
INSERT INTO tblrolesspermisos VALUES(4, 18);
INSERT INTO tblrolesspermisos VALUES(4, 19);
INSERT INTO tblrolesspermisos VALUES(4, 20);
INSERT INTO tblrolesspermisos VALUES(4, 21);
INSERT INTO tblrolesspermisos VALUES(4, 22);
INSERT INTO tblrolesspermisos VALUES(4, 23);
INSERT INTO tblrolesspermisos VALUES(4, 24);
INSERT INTO tblrolesspermisos VALUES(4, 25);
INSERT INTO tblrolesspermisos VALUES(4, 26);
INSERT INTO tblrolesspermisos VALUES(4, 27);
INSERT INTO tblrolesspermisos VALUES(4, 28);
INSERT INTO tblrolesspermisos VALUES(4, 29);
INSERT INTO tblrolesspermisos VALUES(4, 30);
INSERT INTO tblrolesspermisos VALUES(4, 31);
INSERT INTO tblrolesspermisos VALUES(4, 32);
INSERT INTO tblrolesspermisos VALUES(4, 33);
INSERT INTO tblrolesspermisos VALUES(4, 34);
INSERT INTO tblrolesspermisos VALUES(4, 35);
INSERT INTO tblrolesspermisos VALUES(4, 36);
INSERT INTO tblrolesspermisos VALUES(4, 37);
INSERT INTO tblrolesspermisos VALUES(4, 38);
INSERT INTO tblrolesspermisos VALUES(4, 39);
INSERT INTO tblrolesspermisos VALUES(4, 40);
INSERT INTO tblrolesspermisos VALUES(4, 41);
INSERT INTO tblrolesspermisos VALUES(4, 42);
INSERT INTO tblrolesspermisos VALUES(4, 43);
INSERT INTO tblrolesspermisos VALUES(4, 44);