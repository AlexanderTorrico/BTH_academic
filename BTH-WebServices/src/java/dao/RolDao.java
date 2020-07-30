
package dao;

import dto.Rol;
import dto.RolPermiso;
import dto.UserRoles;
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
    
    public abstract int eliminarRolPermiso(String permiso, String tipo, int idPermiso);
    
    public abstract int insertarRoPermiso(String permiso, String tipo, int idPermiso);
    
    public abstract ArrayList<RolPermiso> getListRolPermisos();
    
    public abstract int asignarColegio(UserRoles rolUser);
    
}
