package dao;

import com.sun.prism.Mesh;
import consola.MessageCorreo;
import consola.SendEmail;
import dal.Conexion;
import dto.Docente;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarceloVillca
 */
public class DocenteDaoMySQL extends DocenteDao {

    @Override
    public int insert(Docente obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblusuarios VALUES (");
        query.append("NULL,");
        query.append("'" + obj.getNombre() + "',");
        query.append("'" + obj.getApaterno() + "',");
        query.append("'" + obj.getAmaterno() + "',");
        query.append("'" + obj.getCorreo() + "',");
        query.append("'" + obj.getUsername() + "',");
        query.append("hex(aes_encrypt('" + obj.getContrasenia() + "','DOC')),");
        query.append("" + obj.getEstado() + "");
        query.append(")");
        
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Docente obj) throws Exception {

    }

    @Override
    public void delete(int id) {
    }

    @Override
    public ArrayList<Docente> getList() {
        ArrayList<Docente> lista = new ArrayList<Docente>();

        String query = "select * from tbldocentes";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Docente obj = new Docente();

                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setApaterno(objResultSet.getString("aPaterno"));
                obj.setAmaterno(objResultSet.getString("aMaterno"));
                obj.setCorreo(objResultSet.getString("correo"));
                obj.setUsername(objResultSet.getString("username"));
                obj.setContrasenia(objResultSet.getString("contrasenia"));
                obj.setEstado(objResultSet.getInt("estado"));

                lista.add(obj);
            }
        } catch (Exception ex) {
            try {
                throw new Exception("El registro no pudo ser obtenido " + query);
            } catch (Exception ex1) {
                Logger.getLogger(CarreraDaoMySQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return lista;
    }

    @Override
    public Docente get(int id) {
        return null;
    }

    @Override
    public Docente get(String _username, String _correo) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select * from tblusuarios\n"
                    + "where username='" + _username + "' or correo = '" + _correo + "';";
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Docente obj = new Docente();
                int idDocente = objResultSet.getInt("id");
                obj.setId(idDocente);

                
                String correo = objResultSet.getString("correo");
                obj.setCorreo(correo);

                String username = objResultSet.getString("username");
                obj.setUsername(username);

                

                int estado = objResultSet.getInt("estado");
                obj.setEstado(estado);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    //se manda un link al correo para verificar la cuenta
    @Override
    public void ValidarCuenta(Docente obj) throws Exception {
        SendEmail enviarCorreo = new SendEmail();
        MessageCorreo correo = new MessageCorreo(obj.getCorreo(), obj.getUsername(), obj.getId(), "VERIFICAR CUENTA", "docente");
        enviarCorreo.SendEmail(correo.getCorreo(), correo.getAsunto(), correo.getVerificacion());
    }

    // se actualiza el estado de la cuenta una vez confirmada la verificacion
    @Override
    public void ActivarCuenta(String username) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tblusuarios SET estado = 1 WHERE username = '" + username + "'");
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

}
