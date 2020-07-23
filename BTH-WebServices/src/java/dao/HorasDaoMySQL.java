package dao;

import dal.Conexion;
import dto.Horas;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEO
 */
public class HorasDaoMySQL extends HorasDao {

    @Override
    public int insert(Horas obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblhoras (id, inicio, fin) VALUES (");
        query.append("default,");
        query.append("'" + obj.getInicio() + "',");
        query.append("'" + obj.getFin() + "'");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado " + query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public int update(Horas obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tblhoras SET inicio='" + obj.getInicio() + "', fin= '" + obj.getFin() + "' WHERE id= " + obj.getId());

        id = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return id;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblhoras where id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Horas> getList() {
        ArrayList<Horas> lista = new ArrayList<Horas>();

        String query = "select * from tblhoras";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Horas obj = new Horas();

                obj.setId(objResultSet.getInt("id"));
                obj.setInicio(objResultSet.getTime("inicio"));
                obj.setFin(objResultSet.getTime("fin"));

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
    public Horas get(int id) {

        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tblhoras WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Horas obj = new Horas();
                int _idHoras = objResultSet.getInt("id");
                obj.setId(_idHoras);

                Time _Inicio = objResultSet.getTime("inicio");
                obj.setInicio(_Inicio);

                Time _Fin = objResultSet.getTime("fin");
                obj.setFin(_Fin);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;

    }
}
