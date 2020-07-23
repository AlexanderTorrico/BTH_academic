/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.MovilTrimestre;
import dto.Pago;
import dto.Parametro;
import dto.PlanPagos;
import dto.Search;
import dto.User;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public class PagoDaoMysql extends PagoDao {

    @Override
    public ArrayList<MovilTrimestre> get(User param) throws Exception {
        ArrayList<MovilTrimestre> lista = new ArrayList<MovilTrimestre>();

        String query = "SELECT p.mes, p.monto, p.fecha from (select * from tblEstudiantes_grupos "
                + "                                    where idEstudiante = " + param.getId() + " and idGrupo = " + param.getIdGrupo() + ") ec "
                + "                                join tblPagos p on p.idEstudiantes_grupos = ec.id";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                MovilTrimestre obj = new MovilTrimestre();

                obj.setMes(objResultSet.getInt("mes") + "");

                obj.setMonto(objResultSet.getDouble("monto") + "");

                obj.setFecha("2020-03-24");

                lista.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + " " + query);
        }
        return lista;
    }

    @Override
    public ArrayList<Pago> getMesFaltanteAPagar(Pago param) throws Exception {
        ArrayList<Pago> lista = new ArrayList<Pago>();

        String query = "call sp_deudores(" + param.getIdEstudiantes_grupos() + "," + param.getId() + ");";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Pago obj = new Pago();

                obj.setNombre(objResultSet.getString("nombre"));

                obj.setMes(objResultSet.getInt("meses"));

                lista.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + " " + query);
        }
        return lista;
    }

    @Override
    public int insert(Pago obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblpagos VALUES (null," + obj.getMonto() + "," + obj.getMes() + "," + obj.getFecha() + "," + obj.getIdEstudiantes_grupos() + ")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El pago no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public Search getEstudent(String ci) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select a.id,a.nombre,a.aPaterno,a.aMaterno,a.ci,b.id as grupo, c.nombre as carrera from tblestudiantes a\n"
                    + "inner join tblestudiantes_grupos b\n"
                    + "on b.idEstudiante = a.id\n"
                    + "inner join tblcarreras c\n"
                    + "on c.id = a.idCarrera\n"
                    + "where a.ci =" + ci;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Search obj = new Search();
                int Id = objResultSet.getInt("id");
                obj.setId(Id);

                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);

                String _apellido = objResultSet.getString("aPaterno");
                obj.setApaterno(_apellido);

                String _amaterno = objResultSet.getString("aMaterno");
                obj.setAmaterno(_amaterno);

                String _ci = objResultSet.getString("ci");
                obj.setCi(_ci);

                int _grupo = objResultSet.getInt("grupo");
                obj.setGrupo(_grupo);

                String _carrera = objResultSet.getString("carrera");
                obj.setCarrera(_carrera);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public ArrayList<PlanPagos> getPlanPagos(String ci) throws Exception {

        ArrayList<PlanPagos> lista = new ArrayList<PlanPagos>();

        String query = "call getplanpagos('" + ci + "');";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                PlanPagos obj = new PlanPagos();
                obj.setCodigo(objResultSet.getInt("codigo"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setCuota(objResultSet.getString("cuota"));
                obj.setPago(objResultSet.getString("pago"));
                obj.setSaldo(objResultSet.getString("saldo"));
                obj.setFecha(objResultSet.getString("fecha"));
                obj.setDuracion(objResultSet.getString("duracion"));
                obj.setCostomateria(objResultSet.getString("costomateria"));
                lista.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + " " + query);
        }
        return lista;
    }

}
