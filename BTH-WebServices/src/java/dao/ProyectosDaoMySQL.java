/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Carrera;
import dto.Proyectos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author carlos clavijo
 */
public class ProyectosDaoMySQL extends ProyectosDao {

    @Override
    public ArrayList<Proyectos> getList() {
        ArrayList<Proyectos> list = new ArrayList<>();
        Conexion objConexion = Conexion.getOrCreate();
        String query = "select p.id, p.nombre, p.descripcion, p.imgJson, c.nombre as carrera from tblproyectos p join tblcarreras c on p.idCarrera=c.id;";
        try {
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Proyectos obj = new Proyectos();
                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setDescripcion(objResultSet.getString("descripcion"));
                obj.setImg(objResultSet.getString("imgJson"));
                obj.setNombreCarrera(objResultSet.getString("carrera"));

                list.add(obj);
            }
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage() + " " + query);
            } catch (Exception ex1) {
                Logger.getLogger(indexInformacionDaoMySQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return list;
    }

    @Override
    public ArrayList<Proyectos> getListPorCarrera(int idCarrera) {
        ArrayList<Proyectos> list = new ArrayList<>();
        Conexion objConexion = Conexion.getOrCreate();
        String query = "select p.id, p.nombre, p.descripcion, p.imgJson, c.nombre as carrera from tblproyectos p join tblcarreras c on p.idCarrera=c.id where p.idCarrera= " + idCarrera;;
        try {
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Proyectos obj = new Proyectos();
                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setDescripcion(objResultSet.getString("descripcion"));
                obj.setImg(objResultSet.getString("imgJson"));
                obj.setNombreCarrera(objResultSet.getString("carrera"));
                list.add(obj);
            }
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage() + " " + query);
            } catch (Exception ex1) {
                Logger.getLogger(indexInformacionDaoMySQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return list;
    }

    @Override
    public int delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblparticipantes ");
        int i = 0;
        try {

            query.append("WHERE idProyecto = " + id + ";");
            objConexion.ejecutarSimple(query.toString());
            query = new StringBuilder("DELETE FROM tblproyectos ");
            query.append("WHERE id = " + id + ";");
            i = objConexion.ejecutarSimple(query.toString());
            objConexion.desconectar();
            return i;
        } catch (Exception e) {
            objConexion.desconectar();
            return i;
        }
    }

    @Override
    public int insert(Proyectos proyecto) {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblproyectos (id, nombre, descripcion, idCarrera) VALUES (");
        query.append("default,");
        query.append("'" + proyecto.getNombre() + "',");
        query.append("'" + proyecto.getDescripcion() + "',");;
        query.append(proyecto.getIdCarrera());
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            try {
                throw new Exception("El registro no pudo ser insertado " + query.toString());
            } catch (Exception ex) {
                Logger.getLogger(ProyectosDaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public int update(Proyectos proyecto) {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tblproyectos SET ");
        query.append("nombre = '" + proyecto.getNombre() + "',");
        query.append("descripcion = '" + proyecto.getDescripcion()+ "',");
        query.append("idCarrera = " + proyecto.getIdCarrera()+ " ");
        query.append("WHERE id = " + proyecto.getId());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            try {
                throw new Exception("El registro no pudo ser actualizado" + query.toString());
            } catch (Exception ex) {
                Logger.getLogger(ProyectosDaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        objConexion.desconectar();
        return upd;
    }

    @Override
    public Proyectos get(int id) {
         try {
            Conexion objConexion = Conexion.getOrCreate();
             String query = "select * from tblproyectos where id = "+id;
  
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Proyectos obj = new Proyectos();
                int _id = objResultSet.getInt("id");
                obj.setId(_id);

                String nombre = objResultSet.getString("nombre");
                obj.setNombre(nombre);

                String descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(descripcion);
                
                return obj;
            }
        } catch (Exception ex) {
            ;
        }

        return null;
    }

}
