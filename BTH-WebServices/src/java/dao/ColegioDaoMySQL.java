package dao;

import consola.MessageCorreo;
import consola.SendEmail;
import dal.Conexion;
import dto.Colegio;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class ColegioDaoMySQL extends ColegioDao {

    @Override
    public int insert(Colegio obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblcolegios VALUES (");
        query.append("NULL,");
        query.append("'" + obj.getNombre() + "',");
        query.append("'" + obj.getSigep() + "',");
        query.append("'" + obj.getDirector() + "',");
        query.append("'" + obj.getDireccion() + "',");
        query.append("'" + obj.getTelefono() + "',");
        query.append("1,");
        query.append("'" + obj.getCorreo() + "',");
        query.append("'" + obj.getUsername() + "',");
        query.append("hex(aes_encrypt('" + obj.getContrasenia() + "','COL')),");
        query.append("" + obj.getEstado() + "");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0){
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Colegio obj) throws Exception {

    }

    @Override
    public void delete(int id) {
    }

    @Override
    public ArrayList<Colegio> getList() {
        return null;
    }

    @Override
    public Colegio get(int id) {
        return null;
    }

    @Override
    public Colegio get(String _username, String _correo) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select * from tblcolegios\n"
                    + "where username='" + _username + "' or correo = '" + _correo + "'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Colegio obj = new Colegio();
                int colegioId = objResultSet.getInt("id");
                obj.setId(colegioId);
                String nombre = objResultSet.getString("nombre");
                obj.setNombre(nombre);
                String sigep = objResultSet.getString("sigep");
                obj.setSigep(sigep);
                String director = objResultSet.getString("director");
                obj.setDirector(director);
                String direccion = objResultSet.getString("direccion");
                obj.setDireccion(direccion);
                String telefono = objResultSet.getString("telefono");
                obj.setTelefono(telefono);
                int esModulo = objResultSet.getInt("esModulo");
                obj.setEsModulo(esModulo);
                String correo = objResultSet.getString("correo");
                obj.setCorreo(correo);
                String username = objResultSet.getString("username");
                obj.setUsername(username);
                String contrasenia = objResultSet.getString("contrasenia");
                obj.setContrasenia(contrasenia);
                int estado = objResultSet.getInt("estado");
                obj.setEstado(estado);
                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public void ValidarCuenta(Colegio obj) throws Exception {
        SendEmail enviarCorreo = new SendEmail();
        MessageCorreo correo = new MessageCorreo(obj.getCorreo(), obj.getUsername(), obj.getId(), "VERIFICAR CUENTA","colegio");
        enviarCorreo.SendEmail(correo.getCorreo(), correo.getAsunto(), correo.getVerificacion());
    }

    @Override
    public void ActivarCuenta(String username) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tblcolegios SET estado = 1 WHERE username = '"+username+"'");
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

}
