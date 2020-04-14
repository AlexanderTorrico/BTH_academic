ALTER TABLE tblParametros
  ADD idGrupo int;

 ALTER TABLE tblParametros
 ADD foreign key (idGrupo) references tblGrupos(id);

 ALTER TABLE tblEstudiantes
  ADD idCarrera int;

  ALTER TABLE tblEstudiantes
  ADD foreign key (idCarrera) references TblCarreras(id);