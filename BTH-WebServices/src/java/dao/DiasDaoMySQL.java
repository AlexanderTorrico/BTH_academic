/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Dias;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEO
 */
public class DiasDaoMySQL extends DiasDao {

    @Override
    public int insert(Dias obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tbldias (id, dia, idGrupo, idHora) VALUES (");
        query.append("default,");
        query.append("'" + obj.getDia() + "',");
        query.append("'" + obj.getIdGrupo() + "',");
        query.append("'" + obj.getIdHora() + "'");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado " + query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public int update(Dias obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tbldias SET dia='" + obj.getDia() + "', idGrupo= '" + obj.getIdGrupo() + "',idHora= '" + obj.getIdHora() + "' WHERE id= " + obj.getId());

        id = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return id;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tbldias where id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Dias> getList() {
        ArrayList<Dias> lista = new ArrayList<Dias>();

        String query = "select \n"
                + "D.id , D.dia ,D.idGrupo as Grupo , H.inicio as HoraInicio, H.fin as HoraFin\n"
                + "from tbldias D\n"
                + "left join tblhoras H on D.idHora = H.id;";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Dias obj = new Dias();

                obj.setId(objResultSet.getInt("id"));
                obj.setDia(objResultSet.getInt("dia"));
                obj.setIdGrupo(objResultSet.getInt("Grupo"));
                obj.setHora(objResultSet.getString("HoraInicio"));
                obj.setFin(objResultSet.getString("HoraFin"));

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
    public Dias get(int id) {

        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tbldias WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Dias obj = new Dias();
                int idDias = objResultSet.getInt("id");
                obj.setId(idDias);

                int Dias = objResultSet.getInt("dia");
                obj.setDia(Dias);

                int idGrupos = objResultSet.getInt("idGrupo");
                obj.setIdGrupo(idGrupos);

                int idHora = objResultSet.getInt("idHora");
                obj.setIdHora(idHora);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

}
