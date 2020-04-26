/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Movil;
import dto.User;
import java.sql.ResultSet;

/**
 *
 * @author merxdev
 */
public class MovilInfoEstudianteDaoMysql extends MovilInfoEstudianteDao{

    @Override
    public Movil get(User param) throws Exception {
        String query = "";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            query = "SELECT e.*, eg.id as idEG, c.nombre as deColegio, g.idGestion, g.nivel, c2.nombre as aColegio, concat(d.nombre, ' ',d.aPaterno, ' ', d.aMaterno) as docente, " +
"                                ca.nombre as carrera " +
"                              from (select id, ci, nombre, aPaterno, aMaterno, idColegio from tblEstudiantes " +
"                                  WHERE ci = '110' and contrasenia = '0') e " +
"                                join tblColegios c on e.idColegio = c.id " +
"                                join tblEstudiantes_grupos eg on e.id = eg.idEstudiante " +
"                                join tblgrupos g on eg.idGrupo = g.id " +
"                                join tblColegios c2 on c2.id = g.idColegio " +
"                                join tblDocentes d on d.id = g.idDocente " +
"                                join tblCarreras ca on ca.id = g.idCarrera;";
  
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Movil obj = new Movil();
                
                
                obj.setId(objResultSet.getInt("id"));
                obj.setIdColegio(objResultSet.getInt("idColegio"));

                obj.setCi(objResultSet.getString("nombre"));
                obj.setaPaterno(objResultSet.getString("aPaterno"));
                obj.setaMaterno(objResultSet.getString("aMaterno"));
                obj.setIdEG(objResultSet.getString("idEG"));
                obj.setDeColegio(objResultSet.getString("deColegio"));
                obj.setIdGestion(objResultSet.getString("idGestion"));
                obj.setNivel(objResultSet.getString("nivel"));
                obj.setaColegio(objResultSet.getString("aColegio"));
                obj.setDocente(objResultSet.getString("docente"));
                obj.setCarrera(objResultSet.getString("carrera"));

                return obj;
            }
        } catch (Exception ex) {
            throw new Exception("Error "+ ex.getMessage()+query);
        }

        return null;
    }
    
}
