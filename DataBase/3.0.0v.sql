ALTER TABLE tblGrupos
  ADD costo double;
  
 ALTER TABLE tblGrupos
  ADD inicio date;
  
  ALTER TABLE tblGrupos
  ADD fin date;
  
 UPDATE tblGrupos
SET costo = 1200, inicio = "20200205", fin = "20201123";

UPDATE tblEstudiantes
SET idCarrera = 1;

$$