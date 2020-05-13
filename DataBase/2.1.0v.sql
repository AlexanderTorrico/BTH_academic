ALTER TABLE tblgrupos_parametros DROP FOREIGN KEY tblgrupos_parametros_ibfk_2;
ALTER TABLE tblgrupos_parametros DROP FOREIGN KEY tblgrupos_parametros_ibfk_1;
ALTER TABLE tblnotas DROP FOREIGN KEY tblnotas_ibfk_2;
ALTER TABLE tblnotas DROP FOREIGN KEY tblnotas_ibfk_2;

ALTER TABLE tblNotas ADD CONSTRAINT tblnotas_ibfk_2 FOREIGN KEY (idParametro_grupo) REFERENCES tblParametros(id);

ALTER TABLE tblNotas modify column nota DOUBLE;




DELIMITER $$
DROP procedure IF EXISTS sp_cursorInsertNote;
CREATE procedure sp_cursorInsertNote(_tipo varchar(1), _trimestre int, _idGrupo int)
BLOCK1: begin
	declare ultimoParametro int default 0;
    declare idParm int;   
    declare no_more_rows1 boolean default FALSE;
    declare cursor1 cursor for select id from tblParametros p
				where p.tipo = _tipo and p.trimestre = _trimestre and p.idGrupo = _idGrupo;

    declare continue handler for not found  
        set no_more_rows1 := TRUE;  

    open cursor1;
    LOOP1: loop
        fetch cursor1
        into  idParm;

        if no_more_rows1 then
            close cursor1;
            leave LOOP1;
        end if;

        BLOCK2: begin
            declare idEstGrup int;
            declare no_more_rows2 boolean default FALSE;
           
            declare cursor2 cursor for select a.id from (select * from tblEstudiantes_grupos eg
									where eg.idGrupo = _idGrupo) a
						left join (select * from tblnotas n
									where n.idParametro_grupo = idParm) b on a.id = b.idEstudiante_grupo
						where b.id is null;

			declare continue handler for not found
               set no_more_rows2 := TRUE;
           
            open cursor2;
            LOOP2: loop
                fetch cursor2
                into  idEstGrup;

                if ultimoParametro != idEstGrup then

                	insert into tblNotas values(default,0,idEstGrup,idParm);
                	set ultimoParametro := idEstGrup;
                end if;

                if no_more_rows2 then
                    close cursor2;
                    leave LOOP2;
                end if;

            end loop LOOP2;
        end BLOCK2;
    end loop LOOP1;
end BLOCK1;$$