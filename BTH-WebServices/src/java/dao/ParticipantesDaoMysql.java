/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Participantes;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public class ParticipantesDaoMysql extends ParticipantesDao {

    @Override
    public void insert(String obj) throws Exception {
        String[] vector = obj.split("-");

        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;

        for (int i = 0; i < vector.length; i++) {
            StringBuilder query = new StringBuilder("INSERT INTO tblparticipantes VALUES (");
            query.append("default,");
            query.append(vector[i] + ",");
            query.append("(select max(id) from tblproyectos))");
            
            id = objConexion.ejecutarInsert(query.toString());
        }
        
        objConexion.desconectar();
         
    }

    @Override
    public ArrayList<Participantes> listByProyect(Participantes obj) throws Exception {
        ArrayList<Participantes> list = new ArrayList<>();
        Conexion objConexion = Conexion.getOrCreate();
        String query = "select e.id, concat(e.apaterno, ' ', amaterno, ' ', nombre) as nombre from tblparticipantes p \n" +
            "join tblEstudiantes e on e.id = p.idEstudiante\n" +
            "where idProyecto = " +obj.getId()+
            " order by nombre";
        try {
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                
                Participantes aux = new Participantes();
                aux.setId(objResultSet.getInt("id"));
                aux.setCadena(objResultSet.getString("nombre"));
                

                list.add(aux);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return list;
    }

}
