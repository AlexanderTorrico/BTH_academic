package dao;

import dto.Colegio;
import dto.Docente;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class ColegioDao {

    public abstract int insert(Colegio obj) throws Exception;

    public abstract void update(Colegio obj) throws Exception;

    public abstract void ValidarCuenta(Colegio obj) throws Exception;

    public abstract void ActivarCuenta(String username) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Colegio> getList();

    public abstract Colegio get(int id);

    public abstract Colegio get(String username, String correo);

}
