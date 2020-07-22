
package dao;

import dal.Conexion;
import dto.Permiso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Carlos Clavijo
 */
public class PermisoDaoMySQL extends PermisoDao{
    
    @Override
    public int insert(Permiso obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblPermisos (id, nombre, tipo) VALUES (");
        query.append("DEFAULT, ");
        query.append("'" + obj.getNombre() + "'");
        query.append("'" + obj.getTipo() + "'");
        query.append(')');
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("No se pudo concretar el Insert " + query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public int update(Permiso obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tblPermisos SET nombre = '" + obj.getNombre() + "' WHERE id = " + obj.getId());
        id = objConexion.ejecutarSimple(query.toString());
        return id;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblPermisos WHERE id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Permiso> getList() {
        ArrayList<Permiso> lista = new ArrayList<Permiso>();
        String query = "SELECT * FROM tblPermisos";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Permiso obj = new Permiso();
                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setTipo(objResultSet.getString("tipo"));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            
        }
        return lista;
    }

    @Override
    public Permiso get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tblPermisos WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Permiso obj = new Permiso();
                int _id = objResultSet.getInt("id");
                obj.setId(_id);
                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);
                String _tipo = objResultSet.getString("tipo");
                obj.setTipo(_tipo);
                return obj;
            }
        } catch(Exception ex) {
            
        }
        return null;
    }
    
}
