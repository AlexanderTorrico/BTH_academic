package dao;

import dto.Colegio;
import dto.Docente;
import dto.Estudiante;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class EstudianteDao {

    public abstract int insert(Estudiante obj) throws Exception;

    public abstract void update(Estudiante obj) throws Exception;

    public abstract void ValidarCuenta(Estudiante obj) throws Exception;

    public abstract void ActivarCuenta(String username) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Estudiante> getList();

    public abstract Estudiante get(String id);

    public abstract Estudiante get(String username, String correo);

    public abstract int insertAlgrupo(int idEstudiante, int idGrupo) throws Exception;

}
