package dao;

import dal.Conexion;
import dto.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Clavijo
 */
public class UsuarioDaoMySQL extends UsuarioDao {

    @Override
    public int insert(Usuario obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblUsuarios (id, nombre, apPaterno, apMaterno, email, password, estado) VALUES (");
        query.append("DEFAULT, ");
        query.append("'" + obj.getNombre() + "', ");
        query.append("'" + obj.getApPaterno() + "', ");
        query.append("'" + obj.getApMaterno() + "', ");
        query.append("'" + obj.getEmail() + "', ");
        query.append("'" + obj.getPassword() + "', ");
        query.append(obj.getEstado());
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("No se pudo concretar el Insert " + query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public int update(Usuario obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tblUsuarios SET nombre = '" + obj.getNombre() + "', apPaterno = '" + obj.getApPaterno() + "', apMaterno = '" + obj.getApMaterno() + "', email = '" + obj.getEmail() + "', password = '" + obj.getPassword() + "', estado = " + obj.getEstado() + ", WHERE id= " + obj.getId());
        id = objConexion.ejecutarSimple(query.toString());
        return id;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblUsuarios WHERE id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Usuario> getList() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String query = "SELECT * FROM tblUsuarios";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Usuario obj = new Usuario();
                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setApPaterno(objResultSet.getString("apPaterno"));
                obj.setApMaterno(objResultSet.getString("apMaterno"));
                obj.setEmail(objResultSet.getString("password"));
                obj.setEstado(objResultSet.getInt("estado"));
                obj.setRoles(verificarRoles(objResultSet.getInt("id"), objConexion, objResultSet));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Usuario get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tblUsuarios WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Usuario obj = new Usuario();
                int _usuarioId = objResultSet.getInt("id");
                obj.setId(_usuarioId);
                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);
                String _apPaterno = objResultSet.getString("apPaterno");
                obj.setApPaterno(_apPaterno);
                String _apMaterno = objResultSet.getString("apMaterno");
                obj.setApPaterno(_apMaterno);
                String _email = objResultSet.getString("email");
                obj.setEmail(_email);
                String _password = objResultSet.getString("password");
                obj.setPassword(_password);
                int _estado = objResultSet.getInt("estado");
                obj.setEstado(_estado);
                ArrayList _roles = verificarRoles(_usuarioId, objConexion, objResultSet);
                obj.setRoles(_roles);
                return obj;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    private ArrayList verificarRoles(int idUsuario, Conexion conexion, ResultSet resultSet) throws SQLException {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        String query = "SELECT idRol FROM tblUsuariosRoles WHERE idUsuario = " + idUsuario;
        resultSet = conexion.ejecutar(query);
        while (resultSet.next()) {
            lista.add(resultSet.getInt("idRol"));
        }
        return lista;
    }

    @Override
    public int eliminarUsuarioRol(String rol, int idUsuario) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblUsuariosRoles WHERE idUsuario = " + idUsuario + " AND idRol = "
                + "(SELECT id FROM tblRoles WHERE nombre = '" + rol + "')");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return i;
    }

    @Override
    public int insertarUsuarioRol(String rol, int idUsuario) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("INSERT INTO tblUsuariosRoles VALUES(DEFAULT, 1, " + idUsuario + ", (SELECT id FROM tblRoles WHERE nombre = '" + rol + "'), 1)");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return i;
    }

}
