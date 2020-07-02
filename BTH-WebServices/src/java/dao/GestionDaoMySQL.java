package dao;

import dal.Conexion;
import dto.Gestion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEO
 */
public class GestionDaoMySQL extends GestionDao {

    @Override
    public int insert(Gestion obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblgestiones (id, mesAPagar) VALUES (");
        query.append("default,");
        query.append("'" + obj.getMesAPagar()+ "'");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado " + query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public int update(Gestion obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tblgestiones SET mesAPagar='" + obj.getMesAPagar()+ "' WHERE id= " + obj.getId());

        id = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return id;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblgestiones where id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Gestion> getList() {
        ArrayList<Gestion> lista = new ArrayList<Gestion>();

        String query = "select * from tblgestiones";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Gestion obj = new Gestion();

                obj.setId(objResultSet.getInt("id"));
                obj.setMesAPagar(objResultSet.getInt("mesAPagar"));
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
    public Gestion get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tblgestiones WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Gestion obj = new Gestion();
                int _usuarioId = objResultSet.getInt("id");
                obj.setId(_usuarioId);

                int _Mes = objResultSet.getInt("mesAPagar");
                obj.setMesAPagar(_Mes);

                return obj;
                
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

}
