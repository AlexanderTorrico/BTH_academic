/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Grupos;
import dto.infoGrupos;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEO
 */
public class GruposDaoMySQL extends GruposDao {

    @Override
    public int insert(Grupos obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblgrupos VALUES(default,'"
                + obj.getNivel() + "','" + obj.getEstado() + "','" + obj.getIdGestion() + "','"
                + obj.getIdColegio() + "','" + obj.getIdCarrera() + "','" + obj.getIdDocente() + "'," + obj.getCosto() + ",'" + obj.getFechaInicio() + "','" + obj.getFechaFin() + "')");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado " + query.toString());
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public int update(Grupos obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tblgrupos SET nivel='" + obj.getNivel() + "',estado= '" + obj.getEstado() + "',idGestion= " + obj.getIdGestion() + ",idColegio= " + obj.getIdColegio() + ",idCarrera= " + obj.getIdCarrera() + ",idDocente= " + obj.getIdDocente() + ", costo=" + obj.getCosto() + ", inicio='" + obj.getFechaInicio() + "', fin='" + obj.getFechaFin() + "' WHERE id= " + obj.getId());

        id = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return id;
    }

    @Override
    public Grupos get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tblgrupos WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Grupos obj = new Grupos();
                int _usuarioId = objResultSet.getInt("id");
                obj.setId(_usuarioId);

                String _nombre = objResultSet.getString("nivel");
                obj.setNivel(_nombre);

                String _Sigla = objResultSet.getString("estado");
                obj.setEstado(_Sigla);

                int idGestion = objResultSet.getInt("idGestion");
                obj.setIdGestion(idGestion);

                int idColegio = objResultSet.getInt("idColegio");
                obj.setIdColegio(idColegio);

                int idCarrera = objResultSet.getInt("idCarrera");
                obj.setIdCarrera(idCarrera);

                int idDocente = objResultSet.getInt("idDocente");
                obj.setIdDocente(idDocente);

                int costo = objResultSet.getInt("costo");
                obj.setCosto(costo);

                Date fechaInicio = objResultSet.getDate("inicio");
                obj.setFechaInicio(fechaInicio);

                Date fechaFin = objResultSet.getDate("fin");
                obj.setFechaFin(fechaFin);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;

    }

    @Override
    public ArrayList<Grupos> getList() {
        ArrayList<Grupos> lista = new ArrayList<Grupos>();

        String query = "select "
                + "G.id,"
                + "G.nivel,"
                + "G.estado,"
                + "Ges.mesAPagar,"
                + "Co.nombre as NombreColegio, "
                + "Ca.nombre as NombreCarrera, "
                + "Doc.nombre as NombreDocente, "
                + "G.costo, "
                + "G.inicio, "
                + "G.fin "
                
                + "from tblgrupos G "
                + "left join tblgestiones Ges On G.idGestion = Ges.id "
                + "left join tblcolegios Co On G.idColegio = CO.id "
                + "left join tblcarreras Ca On G.idCarrera = Ca.id "
                + "left join tbldocentes Doc On G.idDocente = Doc.id order by G.id asc";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Grupos obj = new Grupos();

                obj.setId(objResultSet.getInt("id"));
                obj.setNivel(objResultSet.getString("nivel"));
                obj.setEstado(objResultSet.getString("estado"));
                obj.setGestion(objResultSet.getString("mesAPagar"));
                obj.setColegio(objResultSet.getString("NombreColegio"));
                obj.setCarrera(objResultSet.getString("NombreCarrera"));
                obj.setDocente(objResultSet.getString("NombreDocente"));
                obj.setCosto(objResultSet.getDouble("costo"));
                obj.setFechaInicio(objResultSet.getDate("inicio"));
                obj.setFechaFin(objResultSet.getDate("fin"));

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
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblgrupos where id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

}
