/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Parametro;
import dto.infoGrupos;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public class GrupoDaoMysql extends GrupoDao{

    @Override
    public ArrayList<infoGrupos> grupoInfo(infoGrupos param) throws Exception {
        ArrayList<infoGrupos> list = new ArrayList<>();
        String query = "select g.id, g.nivel, c.sigla, co.nombre, g.idDocente, c.nombre as carrera from tblGrupos g " +
                "join tblCarreras c on c.id = g.idCarrera " +
                "join tblColegios co on co.id = g.idColegio " +
                "where g.idDocente="+param.getIdDocente();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                infoGrupos obj = new infoGrupos();

                obj.setId(objResultSet.getInt("id"));
                
                obj.setIdDocente(objResultSet.getInt("idDocente"));
                
                obj.setNivel(objResultSet.getString("nivel"));

                obj.setSigla(objResultSet.getString("sigla"));

                obj.setNombre(objResultSet.getString("nombre"));
                
                obj.setCarrera(objResultSet.getString("carrera"));
                list.add(obj);
                
            }
        } catch (Exception ex) {
            throw new Exception("El registro no pudo ser obtenido " + query +ex.getMessage());
        }
        return list;
    }
    
}
