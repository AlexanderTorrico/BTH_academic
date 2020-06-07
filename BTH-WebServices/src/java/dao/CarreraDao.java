package dao;

import dto.Carrera;
import dto.Colegio;
import java.util.ArrayList;

/**
 *
 * @author LEO
 */
public abstract class CarreraDao {

    public abstract int insert(Carrera obj) throws Exception;

    public abstract int update(Carrera obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Carrera> getList();

    public abstract Carrera get(int id);

}
