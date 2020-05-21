/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.InformacionesGrupo;
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
    
    
    @Override
    public ArrayList<InformacionesGrupo> docenteWebGrupos(InformacionesGrupo param) throws Exception {
        ArrayList<InformacionesGrupo> list = new ArrayList<>();
        String query = "select a.id, a.idCarrera,a.nivel, a.estado, a.idGestion, c.nombre as colegio, concat(d.nombre, ' ',d.apaterno, ' ', d.amaterno) as docente, " +
"ca.nombre as carrera, ca.sigla from (select * from tblGrupos g " +
"		where idDocente = "+param.getId()+") a " +
"	join tblColegios c on c.id = a.idColegio " +
"	join tblDocentes d on d.id = a.id " +
"	join tblCarreras ca on ca.id = a.idCarrera;";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                InformacionesGrupo obj = new InformacionesGrupo();

                obj.setId(objResultSet.getInt("id"));
                obj.setNivel(objResultSet.getString("nivel"));
                obj.setEstado(objResultSet.getString("estado"));
                obj.setIdGestion(objResultSet.getInt("idGestion"));
                obj.setColegio(objResultSet.getString("colegio"));
                obj.setDocente(objResultSet.getString("docente"));
                obj.setCarrera(objResultSet.getString("carrera"));
                obj.setSigla(objResultSet.getString("sigla"));
                
                list.add(obj);
                
            }
        } catch (Exception ex) {
            throw new Exception("El registro no pudo ser obtenido " + query +ex.getMessage());
        }
        return list;
    }
    

    @Override
    public ArrayList<InformacionesGrupo> docenteWebHorario(InformacionesGrupo param) throws Exception {
        ArrayList<InformacionesGrupo> list = new ArrayList<>();
        String query = "select idGrupo, dia, inicio, fin from tbldias d " +
            "join tblGrupos g on g.id = d.idGrupo " +
            "join tblHoras h on d.idHora = h.id " +
            "where idDocente = "+param.getId();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                InformacionesGrupo obj = new InformacionesGrupo();
                obj.setId(objResultSet.getInt("idGrupo"));
                String[] i = objResultSet.getTime("inicio").toString().split(":");
                String[] f = objResultSet.getTime("fin").toString().split(":");
                obj.setDia(textDia(objResultSet.getInt("dia")));
                obj.setInicio(i[0]+":"+i[1]);
                obj.setFin(f[0]+":"+f[1]);
          
                
                list.add(obj);
                
            }
        } catch (Exception ex) {
            throw new Exception("El registro no pudo ser obtenido " + query +ex.getMessage());
        }
        return list;
    }
    
    
    
    private String textDia(int dia){
        if(dia==1){
            return "Lunes,";
        } else if(dia==2){
            return "Martes,";
        } else if(dia==3){
            return "Miercoles,";
        } else if(dia==4){
            return "Jueves,";
        } else if(dia==5){
            return "Viernes,";
        } else if(dia==6){
            return "Sabado,";
        } else{
            return "Domingo,";
        }
    }
}
