package dao;

import dto.Usuario;
import dto.UserRoles;

/**
 *
 * @author Carlos Clavijo
 */
import java.util.ArrayList;

public abstract class UsuarioDao {
    
    public abstract int insert(Usuario obj) throws Exception;

    public abstract int update(Usuario obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Usuario> getList();

    public abstract Usuario get(int id);
    
    public abstract int eliminarUsuarioRol(String rol, int idUsuario);
    
    public abstract int insertarUsuarioRol(String rol, int idUsuario);
    
    public abstract ArrayList<UserRoles> getRoles(int id);
}
