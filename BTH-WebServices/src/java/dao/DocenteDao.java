package dao;

import dto.Colegio;
import dto.Docente;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class DocenteDao {

    public abstract int insert(Docente obj) throws Exception;

    public abstract void update(Docente obj) throws Exception;

    public abstract void ValidarCuenta(Docente obj) throws Exception;

    public abstract void ActivarCuenta(Docente obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Docente> getList();

    public abstract Docente get(int id);

    public abstract Docente get(String username, String correo);

}
