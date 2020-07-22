
package dao;

import dto.Rol;
import java.util.ArrayList;

/**
 *
 * @author Carlos Clavijo
 */
public abstract class RolDao {
    
    public abstract int insert(Rol obj) throws Exception;

    public abstract int update(Rol obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Rol> getList();

    public abstract Rol get(int id);
    
}
