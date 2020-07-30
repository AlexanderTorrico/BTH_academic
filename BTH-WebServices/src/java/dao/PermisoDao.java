
package dao;

import dto.Permiso;
import java.util.ArrayList;

/**
 *
 * @author Carlos Clavijo
 */
public abstract class PermisoDao {
    
    public abstract int insert(Permiso obj) throws Exception;

    public abstract int update(Permiso obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Permiso> getList();

    public abstract Permiso get(int id);
    
}