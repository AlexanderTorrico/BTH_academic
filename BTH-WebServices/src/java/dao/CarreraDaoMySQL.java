package dao;

import dal.Conexion;
import dto.Carrera;
import dto.Docente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEO
 */
public class CarreraDaoMySQL extends CarreraDao {

    @Override
    public ArrayList<Carrera> getList() {
        ArrayList<Carrera> lista = new ArrayList<Carrera>();

        String query = "select * from tblcarreras";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Carrera obj = new Carrera();

                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setSigla(objResultSet.getString("sigla"));
                obj.setDescripcion(objResultSet.getString("descripcion"));
                obj.setImg(objResultSet.getString("img"));

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
    public int update(Carrera obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tblcarreras SET nombre='" + obj.getNombre() + "', sigla= '" + obj.getSigla() + "',descripcion= '" + obj.getDescripcion() + "',img= '" + obj.getImg() + "' WHERE id= " + obj.getId());

        id = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return id;
    }

    @Override
    public int insert(Carrera obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblcarreras (id, nombre, sigla, descripcion, img) VALUES (");
        query.append("default,");
        query.append("'" + obj.getNombre() + "',");
        query.append("'" + obj.getSigla() + "',");
        query.append("'" + obj.getDescripcion() + "',");
        query.append("'" + obj.getImg() + "'");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado " + query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblcarreras where id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public Carrera get(int id) {

        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tblcarreras WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Carrera obj = new Carrera();
                int _usuarioId = objResultSet.getInt("id");
                obj.setId(_usuarioId);

                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);

                String _Sigla = objResultSet.getString("sigla");
                obj.setSigla(_Sigla);

                String _descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_descripcion);

                String _img = objResultSet.getString("img");
                obj.setImg(_img);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

}
