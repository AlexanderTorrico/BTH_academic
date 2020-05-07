/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Carrera;
import dto.InformacionesCarrera;
import dto.Pago;
import dto.User;
import dto.infoGrupos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos clavijo
 */
public class indexInformacionDaoMySQL extends indexInformacionDao {

    @Override
    public ArrayList<Carrera> getListIndex() {
        ArrayList<Carrera> list = new ArrayList<>();
        Conexion objConexion = Conexion.getOrCreate();
        String query = "select * from tblcarreras;";
        try {
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Carrera obj = new Carrera();
                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setSigla(objResultSet.getString("sigla"));
                obj.setDescripcion(objResultSet.getString("descripcion"));
                obj.setImg(objResultSet.getString("img"));
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
    public ArrayList<InformacionesCarrera> getListInformacion(int id) {
        ArrayList<InformacionesCarrera> list = new ArrayList<>();
        Conexion objConexion = Conexion.getOrCreate();
        String query = "select * from tblinformaciones where idCarrera = " + id ;
        try {
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                InformacionesCarrera obj = new InformacionesCarrera();
                obj.setId(objResultSet.getInt("id"));
                obj.setTitulo(objResultSet.getString("titulo"));
                obj.setDescripcion(objResultSet.getString("descripcion"));
                obj.setImg(objResultSet.getString("img"));
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
}
