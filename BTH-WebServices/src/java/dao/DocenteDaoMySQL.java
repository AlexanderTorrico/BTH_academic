package dao;

import com.sun.prism.Mesh;
import consola.MessageCorreo;
import consola.SendEmail;
import dal.Conexion;
import dto.Docente;
import dto.Usuario;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author MarceloVillca
 */
public class DocenteDaoMySQL extends DocenteDao {

    @Override
    public int insert(Docente obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO TBLDOCENTES VALUES (");
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
        return null;
    }

    @Override
    public Docente get(int id) {
        return null;
    }

    @Override
    public Docente get(String _username, String _correo) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select * from tbldocentes\n"
                    + "where username='" + _username + "' or correo = '" + _correo + "';";
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Docente obj = new Docente();
                int idDocente = objResultSet.getInt("id");
                obj.setId(idDocente);

                String nombre = objResultSet.getString("nombre");
                obj.setNombre(nombre);

                String paterno = objResultSet.getString("apaterno");
                obj.setApaterno(paterno);

                String materno = objResultSet.getString("amaterno");
                obj.setAmaterno(materno);

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

    //se manda un link al correo para verificar la cuenta
    @Override
    public void ValidarCuenta(Docente obj) throws Exception {
        SendEmail enviarCorreo = new SendEmail();
        MessageCorreo correo = new MessageCorreo(obj.getCorreo(), obj.getUsername(), obj.getId(), "VERIFICAR CUENTA");
        enviarCorreo.SendEmail(correo.getCorreo(), correo.getAsunto(),correo.getVerificacion());
    }

    // se actualiza el estado de la cuenta una vez confirmada la verificacion
    @Override
    public void ActivarCuenta(Docente obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tbldocentes SET estado = " + obj.getEstado()
                + " where correo = " + obj.getCorreo()
                + " and username =" + obj.getUsername() + "");
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

}