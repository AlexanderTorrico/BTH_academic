package dao;

import dal.Conexion;
import dto.Rol;
import dto.RolPermiso;
import dto.UserRoles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Carlos Clavijo
 */
public class RolDaoMySQL extends RolDao {

    @Override
    public int insert(Rol obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblRoles (id, nombre) VALUES (");
        query.append("DEFAULT, ");
        query.append("'" + obj.getNombre() + "'");
        query.append(')');
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("No se pudo concretar el Insert " + query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public int update(Rol obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tblRoles SET nombre = '" + obj.getNombre() + "' WHERE id = " + obj.getId());
        id = objConexion.ejecutarSimple(query.toString());
        return id;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblRoles WHERE id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Rol> getList() {
        ArrayList<Rol> lista = new ArrayList<Rol>();
        String query = "SELECT * FROM tblRoles";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Rol obj = new Rol();
                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                lista.add(obj);
            }
        } catch (SQLException ex) {

        }
        return lista;
    }

    @Override
    public Rol get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tblRoles WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Rol obj = new Rol();
                int _id = objResultSet.getInt("id");
                obj.setId(_id);
                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);
                return obj;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public int eliminarRolPermiso(String permiso, String tipo, int idRol) {
        Conexion objConexion = Conexion.getOrCreate();
        String eliminar1 = "";
        String eliminar2 = "";
        System.out.println(tipo);
        if ("SoloEscritura".equals(tipo)) {
            System.out.println("Funciona");
            eliminar1 += "SoloLectura";
            eliminar2 += "EscrituraLectura";
        } else if ("SoloLectura".equals(tipo)) {
            eliminar1 += "SoloEscritura";
            eliminar2 += "EscrituraLectura";
        } else if ("EscrituraLectura".equals(tipo)) {
            eliminar1 += "SoloLectura";
            eliminar2 += "SoloEscritura";
        }
        StringBuilder query = new StringBuilder("DELETE FROM tblRolesPermisos WHERE idRol = " + idRol + " AND idPermiso = "
                + "(SELECT id FROM tblPermisos WHERE nombre = '" + permiso + "' AND tipo = '" + eliminar1 + "')");
        System.out.println(query);
        int i = objConexion.ejecutarSimple(query.toString());
        query = new StringBuilder("DELETE FROM tblRolesPermisos WHERE idRol = " + idRol + " AND idPermiso = "
                + "(SELECT id FROM tblPermisos WHERE nombre = '" + permiso + "' AND tipo = '" + eliminar2 + "')");
        System.out.println(query);
        i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return i;
    }

    @Override
    public int insertarRoPermiso(String permiso, String tipo, int idRol) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("INSERT INTO tblRolesPermisos VALUES(" + idRol + ",(SELECT id FROM tblPermisos "
                + "WHERE nombre = '" + permiso + "' AND tipo = '" + tipo + "'))");
        System.out.println(query);
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return i;
    }

    @Override
    public ArrayList<RolPermiso> getListRolPermisos() {
        ArrayList<RolPermiso> lista = new ArrayList<RolPermiso>();
        String query = "SELECT * FROM tblRolesPermisos";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                RolPermiso obj = new RolPermiso();
                obj.setIdRol(objResultSet.getInt("idRol"));
                obj.setIdPermiso(objResultSet.getInt("idPermiso"));
                lista.add(obj);
            }
        } catch (SQLException ex) {

        }
        return lista;
    }

    @Override
    public int asignarColegio(UserRoles rolUser) {
        String query = "UPDATE * FROM tblUsuariosRoles SET idreferece = " + rolUser.getIdReferencia() +
                " WHERE idUsuario = " + rolUser.getIdUsuario();
        System.out.println(query);
        Conexion objConexion = Conexion.getOrCreate();
        ResultSet objResultSet = objConexion.ejecutar(query);
        return 0;
    }

}
