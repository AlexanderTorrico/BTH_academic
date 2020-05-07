package dao;

import com.sun.prism.Mesh;
import consola.MessageCorreo;
import consola.SendEmail;
import dal.Conexion;
import dto.Docente;
import dto.Estudiante;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author MarceloVillca
 */
public class EstudianteDaoMySQL extends EstudianteDao {

    @Override
    public int insert(Estudiante obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO TBLESTUDIANTES VALUES (null,'"+obj.getCi()+"','"+obj.getNombre()+"','"+obj.getApaterno()+"','"+obj.getAmaterno()+"','"+obj.getContrasenia()+"','"+obj.getTelefono()+"','"+obj.getGenero()+"','"+obj.getNacimiento()+"','"+obj.getEstado()+"',"+obj.getIdColegio()+","+obj.getIdCarrera()+")");
      
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Estudiante obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ValidarCuenta(Estudiante obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ActivarCuenta(String username) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Estudiante> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estudiante get(String id) {
       try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select * from tblestudiantes where ci = "+id+"";
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Estudiante obj = new Estudiante();
                int idEstudiante = objResultSet.getInt("id");
                obj.setId(idEstudiante);
                String ci = objResultSet.getString("ci");
                obj.setCi(ci);      
                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public Estudiante get(String username, String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
