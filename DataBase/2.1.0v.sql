ALTER TABLE tblgrupos_parametros DROP FOREIGN KEY tblgrupos_parametros_ibfk_2;
ALTER TABLE tblgrupos_parametros DROP FOREIGN KEY tblgrupos_parametros_ibfk_1;
ALTER TABLE tblnotas DROP FOREIGN KEY tblnotas_ibfk_2;
ALTER TABLE tblnotas DROP FOREIGN KEY tblnotas_ibfk_2;

ALTER TABLE tblNotas ADD CONSTRAINT tblnotas_ibfk_2 FOREIGN KEY (idParametro_grupo) REFERENCES tblParametros(id);

ALTER TABLE tblNotas modify column nota DOUBLE;




DELIMITER $$

DROP FUNCTION IF EXISTS fn_changePassword;
CREATE FUNCTION fn_changePassword(_email varchar(50), _sha text, _tipo varchar(1), _pass text)
RETURNS varchar(50)
BEGIN
    declare v_date varchar(50);
    set v_date ="null";

    SELECT t.email  into v_date from tblTokens t
        WHERE t.email = _email and t.sha = _sha;

    if v_date != 'null' then
        if _tipo = 'c' then
            delete from tblTokens where email = v_date;
            UPDATE tblColegios
            SET contrasenia=hex(aes_encrypt(_pass,'COL')) WHERE correo=_email;
        
        end if;

    end if;

    return v_date;
END$$

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






DROP PROCEDURE IF EXISTS sp_notasAnuales;
CREATE PROCEDURE sp_notasAnuales(_idGrupo int)
BEGIN  
	select est.*, ifnull(nota.primer,0) as primer, ifnull(nota.segundo,0) as segundo, ifnull(nota.tercer,0) as tercero from (select e.id, concat(e.apaterno,' ',e.amaterno,' ',e.nombre) as nombre from tblEstudiantes e
		join tblEstudiantes_grupos eg on e.id = eg.idEstudiante
		where idGrupo = _idGrupo) est
	left join (select x.*, y.segundo, z.tercer from (select a1.id, (a1.nota+b1.nota+c1.nota)/3 as primer from (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=1 and idGrupo =_idGrupo and tipo = 'h') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) a1
			join (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=1 and idGrupo =_idGrupo and tipo = 'c') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) b1 on a1.id = b1.id
			join (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=1 and idGrupo =_idGrupo and tipo = 's') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) c1 on a1.id = c1.id) x
		join (select a1.id, (a1.nota+b1.nota+c1.nota)/3 as segundo from (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=2 and idGrupo =_idGrupo and tipo = 'h') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) a1
			join (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=2 and idGrupo =_idGrupo and tipo = 'c') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) b1 on a1.id = b1.id
			join (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=2 and idGrupo =_idGrupo and tipo = 's') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) c1 on a1.id = c1.id) y on x.id = y.id
		join (select a1.id, (a1.nota+b1.nota+c1.nota)/3 as tercer from (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=3 and idGrupo =_idGrupo and tipo = 'h') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) a1
			join (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=3 and idGrupo =_idGrupo and tipo = 'c') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) b1 on a1.id = b1.id
			join (select n.idEstudiante_grupo as id, avg(n.nota) as nota from (SELECT * from tblparametros
							where trimestre=3 and idGrupo =_idGrupo and tipo = 's') a
				join tblnotas n on n.idParametro_grupo = a.id
				group by n.idEstudiante_grupo) c1 on a1.id = c1.id) z on x.id = z.id) nota on est.id = nota.id
	order by nombre;

END $$

