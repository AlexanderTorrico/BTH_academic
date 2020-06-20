/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Asistencia;
import java.util.ArrayList;
import dal.Conexion;
import dto.Estudiante;
import dto.Grupos;
import java.awt.Dialog;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author merxdev
 */
public class AsistenciaDaoMysql extends AsistenciaDao {

    @Override
    public ArrayList<Asistencia> getDateByGroup(int id) throws Exception {
        ArrayList<Asistencia> lista = new ArrayList<>();

        String query = "select distinct(fecha) from tblestudiantes_grupos e "
                + "join tblAsistencias a on e.id = a.idestudiantes_grupos "
                + "where idGrupo=" + id + " order by fecha";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Asistencia obj = new Asistencia();

                obj.setFecha(objResultSet.getDate("fecha"));

                lista.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + " -- " + query);
        }
        return lista;
    }

    @Override
    public ArrayList<Asistencia> getlistAssistance(Asistencia obj) throws Exception {
        ArrayList<Asistencia> lista = new ArrayList<>();
        obj.getFecha().setDate(obj.getFecha().getDate() + 1);
        String query = "select x.id, concat(e.nombre,' ', aPaterno, ' ', aMaterno) as nombre, c.nombre as colegio, x.tipo as asistencia from tblestudiantes e "
                + "join tblColegios c on c.id = e.idColegio "
                + "join (select b.id, a.idEstudiante, tipo from (select * from tblestudiantes_grupos where idGrupo=" + obj.getId() + ") a "
                + "      join (select * from tblAsistencias where fecha = '" + obj.getFecha() + "')b on a.id = b.idestudiantes_grupos) x on e.id = x.idEstudiante "
                + "order by nombre;";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Asistencia newObj = new Asistencia();

                newObj.setId(objResultSet.getInt("id"));
                newObj.setNombre(objResultSet.getString("nombre"));
                newObj.setColegio(objResultSet.getString("colegio"));
                newObj.setTipo(objResultSet.getString("asistencia"));
                newObj.setFecha(obj.getFecha());

                lista.add(newObj);
            }
        } catch (Exception ex) {

            throw new Exception(ex.getMessage());
        }

        return lista;
    }

    @Override
    public int insertAsistenciaByGroup(Asistencia obj) throws Exception {
        obj.getFecha().setDate(obj.getFecha().getDate() + 1);
        Conexion objConexion = Conexion.getOrCreate();

        GrupoDaoMysql grupo = new GrupoDaoMysql();
        Grupos gp = new Grupos();
        gp.setId(obj.getId());

        ArrayList<Estudiante> listaEstudiante = listId(obj.getId(), objConexion);
        try {
            if (existDate(obj, objConexion) == 0) {
                for (Estudiante e : listaEstudiante) {
                    int id = 0;
                    StringBuilder query = new StringBuilder("INSERT INTO tblasistencias VALUES "
                            + "(default,'p',null,'" + obj.getFecha() + "','1'," + e.getId() + ")");

                    id = objConexion.ejecutarInsert(query.toString());
                }
            }else{
                throw new Exception("La fecha seleccionada ya existe.");
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

        objConexion.desconectar();
        return 1;
    }

    private int existDate(Asistencia obj, Conexion objConexion) throws Exception {
        ResultSet objResultSet = null;
        String query = "";
        try {
            
            query = "select count(*) as id from(select * from tblestudiantes_grupos "
                    + "      where idGrupo = " + obj.getId() + ") a "
                    + "join (select * from tblAsistencias where fecha = '" + obj.getFecha() + "') b on a.id = b.idestudiantes_grupos";

            objResultSet = objConexion.ejecutar(query);
            objResultSet.next();
            return objResultSet.getInt("id");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + query);
        }

    }

    private ArrayList<Estudiante> listId(int idGroup, Conexion objConexion) throws Exception {
        ArrayList<Estudiante> lista = new ArrayList<>();

        String query = "select * from tblestudiantes_grupos "
                + "where idGrupo = " + idGroup;

        try {

            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Estudiante newObj = new Estudiante();

                newObj.setId(objResultSet.getInt("id"));

                lista.add(newObj);
            }
        } catch (Exception ex) {

            throw new Exception(ex.getMessage() + "rara");
        }

        return lista;
    }

    @Override
    public int updateAssistance(Asistencia obj) throws Exception {

        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("update tblAsistencias set tipo = '" + obj.getTipo() + "' "
                + "where id = " + obj.getId());

        id = objConexion.ejecutarInsert(query.toString());

        objConexion.desconectar();
        return 1;
    }

    @Override
    public ArrayList<Asistencia> listAssistanceCount(Asistencia obj) throws Exception {
        ArrayList<Asistencia> lista = new ArrayList<>();
        
        String query = "select concat(e.aPaterno, ' ', e.aMaterno,' ',e.nombre) as nombre, l.* from tblestudiantes e " +
"join(select x.idEstudiante, presente, falta, retraso from (select eg.idEstudiante, count(tipo) as presente from (select * from tblestudiantes_grupos " +
"                  where idGrupo="+obj.getId()+") eg " +
"            left join (select * from tblAsistencias where tipo = 'p') a on eg.id = a.idestudiantes_grupos " +
"            group by eg.idEstudiante) x " +
"join (select eg.idEstudiante, count(tipo) as falta from (select * from tblestudiantes_grupos " +
"            where idGrupo="+obj.getId()+") eg " +
"      left join (select * from tblAsistencias where tipo = 'f') a on eg.id = a.idestudiantes_grupos " +
"      group by eg.idEstudiante) y on x.idEstudiante = y.idEstudiante " +
"join(select eg.idEstudiante, count(tipo) as retraso from (select * from tblestudiantes_grupos " +
"                  where idGrupo="+obj.getId()+") eg " +
"            left join (select * from tblAsistencias where tipo = 'r') a on eg.id = a.idestudiantes_grupos " +
"      group by eg.idEstudiante) z on x.idEstudiante = z.idEstudiante)l on e.id = l.idEstudiante " +
"order by nombre";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Asistencia newObj = new Asistencia();

                newObj.setId(objResultSet.getInt("idEstudiante"));
                newObj.setNombre(objResultSet.getString("nombre"));
                newObj.setPresente(objResultSet.getInt("presente"));
                newObj.setRetraso(objResultSet.getInt("retraso"));
                newObj.setFalta(objResultSet.getInt("falta"));
                newObj.setFecha(obj.getFecha());

                lista.add(newObj);
            }
        } catch (Exception ex) {

            throw new Exception(ex.getMessage());
        }

        return lista;
    }
}
