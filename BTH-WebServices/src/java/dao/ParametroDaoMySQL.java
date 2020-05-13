/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Parametro;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Derxal
 */
public class ParametroDaoMySQL extends ParametroDao{

    @Override
    public int insert(Parametro obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblparametros VALUES (");
        query.append("default,");
        query.append("'" + obj.getNombre() + "',");
        query.append("'" + obj.getTipo()+"',");
        query.append("'" + obj.getTrimestre()+"',");
        query.append(obj.getIdGrupo());
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado "+query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public ArrayList<Parametro> getList() throws Exception {
        
        ArrayList<Parametro> lista = new ArrayList<Parametro>();

        String query = "select * from tblParametros";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Parametro obj = new Parametro();

                obj.setId(objResultSet.getInt("id"));

                obj.setNombre(objResultSet.getString("nombre"));

                obj.setTipo(objResultSet.getString("tipo"));

                lista.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception("El registro no pudo ser obtenido " + query);
        }
        return lista;
    }

    @Override
    public ArrayList<Parametro> getByGrupo(Parametro param) throws Exception {
        ArrayList<Parametro> list = new ArrayList<>();
        String query = "select p.* from tblGrupos g " +
            "join tblParametros p on g.id = p.idGrupo " +
            "where g.id = "+param.getIdGrupo()+" and p.trimestre = '"+param.getTrimestre()+"'";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Parametro obj = new Parametro();

                obj.setId(objResultSet.getInt("id"));

                obj.setNombre(objResultSet.getString("nombre"));

                obj.setTipo(objResultSet.getString("tipo"));

                list.add(obj);
                
            }
        } catch (Exception ex) {
            throw new Exception("El registro no pudo ser obtenido " + query);
        }
        return list;
    }
    
    @Override
    public int delete(int id) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblParametros ");
        int i = 0;
        try {

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
    public int update(Parametro obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tblParametros SET ");
        query.append("nombre = '" + obj.getNombre()+"'");
        query.append("WHERE id = " + obj.getId());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado"+query.toString());
        }
        
        objConexion.desconectar();
        return upd;
    }
    
    
    /*   Ver notas  */
    @Override
    public ArrayList<Parametro> getByGrupoTable(Parametro param) throws Exception {
        ArrayList<Parametro> list = new ArrayList<>();
        String query = "SELECT id, nombre from tblParametros " +
            "where idGrupo = "+param.getIdGrupo()+" and tipo = '"+param.getTipo()+"' and trimestre = "+param.getTrimestre();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Parametro obj = new Parametro();

                obj.setId(objResultSet.getInt("id"));

                obj.setNombre(objResultSet.getString("nombre"));

                list.add(obj);
                
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage()+" - " + query);
        }
        return list;
    }
}
