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
    
    @Override
    public ArrayList<Proyectos> getListByGroup(Proyectos proyecto)throws Exception {
        ArrayList<Proyectos> list = new ArrayList<>();
        Conexion objConexion = Conexion.getOrCreate();
        String query = "select p.*, g.idGestion, c.nombre as colegio, ca.nombre as carrera from tblproyectos p\n" +
        "join tblgrupos g on p.idcarrera = g.id\n" +
        "join tblcolegios c on c.id = g.idColegio\n" +
        "join tblcarreras ca on ca.id = g.idcarrera\n" +
        "join tblDocentes d on d.id = g.idDocente\n" +
        "where d.id ="+proyecto.getId()+" and g.idGestion = "+proyecto.getIdgestion();
        try {
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                String auxImg = objResultSet.getString("imgJson");
                if(auxImg==null){
                    auxImg = "0";
                }else if(auxImg.isEmpty()){
                    auxImg = "0";
                }else{
                    auxImg = objResultSet.getString("imgJson");
                }
                Proyectos obj = new Proyectos();
                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setDescripcion(objResultSet.getString("descripcion"));
                obj.setImg(auxImg);
                obj.setNombreCarrera(objResultSet.getString("carrera"));
                obj.setNombreColegio(objResultSet.getString("colegio"));
                obj.setEstado(objResultSet.getBoolean("estado"));

                list.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return list;
    }

    @Override
    public String deleteProyectAndPartaker(Proyectos proyecto) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder queryParticipante = new StringBuilder("delete from tblparticipantes where idProyecto = "+proyecto.getId());
        StringBuilder queryProyecto = new StringBuilder("delete from tblproyectos where id = "+proyecto.getId());
        int i = 0;
        try {

            
            objConexion.ejecutarSimple(queryParticipante.toString());
            objConexion.ejecutarSimple(queryProyecto.toString());
            
            
            objConexion.desconectar();
            return "Proyecto eliminado";
        } catch (Exception e) {
            objConexion.desconectar();
            return e.getMessage();
        }
    }

    @Override
    public String isActive(Proyectos proyecto) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("update tblproyectos set estado = "+proyecto.isEstado()+" where id ="+proyecto.getId());
        
        int i = 0;
        try {

            
            objConexion.ejecutarSimple(query.toString());
            
            
            
            objConexion.desconectar();
            return "Proyecto actualizado"+query.toString();
        } catch (Exception e) {
            objConexion.desconectar();
            return e.getMessage();
        }
    }

    @Override
    public ArrayList<Proyectos> getListByGroupEstado(Proyectos proyecto) throws Exception {
        ArrayList<Proyectos> list = new ArrayList<>();
        Conexion objConexion = Conexion.getOrCreate();
        String query = "select p.*, g.idGestion, c.nombre as colegio, ca.nombre as carrera from tblproyectos p\n" +
            "join tblgrupos g on p.idcarrera = g.id\n" +
            "join tblcolegios c on c.id = g.idColegio\n" +
            "join tblcarreras ca on ca.id = g.idcarrera\n" +
            "join tblDocentes d on d.id = g.idDocente\n" +
            "where ca.id ="+proyecto.getId()+" and g.idGestion = "+proyecto.getIdgestion()+" and p.estado = true;";
        try {
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                String auxImg = objResultSet.getString("imgJson");
                if(auxImg==null){
                    auxImg = "0";
                }else if(auxImg.isEmpty()){
                    auxImg = "0";
                }else{
                    auxImg = objResultSet.getString("imgJson");
                }
                Proyectos obj = new Proyectos();
                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setDescripcion(objResultSet.getString("descripcion"));
                obj.setImg(auxImg);
                obj.setNombreCarrera(objResultSet.getString("carrera"));
                obj.setNombreColegio(objResultSet.getString("colegio"));
                obj.setEstado(objResultSet.getBoolean("estado"));

                list.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return list;
    }

}
