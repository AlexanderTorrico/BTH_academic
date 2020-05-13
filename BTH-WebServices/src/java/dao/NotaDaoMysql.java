/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Nota;
import dto.Parametro;
import dto.infoGrupos;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public class NotaDaoMysql extends NotaDao{

    @Override
    public ArrayList<Nota> get(Parametro param) throws Exception {
        
        completeInsert(param);
        
        ArrayList<Nota> list = new ArrayList<>();
        String query = "select c.id , c.nota, concat(e.aPaterno,' ',e.amaterno,' ', e.nombre) as nombreCompleto,c.nombre as parametro from (SELECT * from (SELECT n.* from tblEstudiantes_grupos eg " +
"					join tblnotas n on eg.id = n.idEstudiante_grupo " +
"					where idGrupo ="+param.getIdGrupo()+") a " +
"			join (select p.id as idParm, p.nombre from tblParametros p " +
"					where tipo = '"+param.getTipo()+"' and trimestre = "+param.getTrimestre()+" and idGrupo = "+param.getIdGrupo()+") b on a.idParametro_grupo = b.idParm) c " +
"	left join tblEstudiantes e on c.idEstudiante_grupo = e.id " +
"order by c.idParm,  e.aPaterno, e.aMaterno, e.nombre;";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Nota obj = new Nota();

                obj.setId(objResultSet.getInt("id"));
                
                obj.setNota(objResultSet.getDouble("nota"));
                
                obj.setNombre(objResultSet.getString("nombreCompleto"));
                
                obj.setParametro(objResultSet.getString("parametro"));
                
                list.add(obj);
                
            }
        } catch (Exception ex) {
            
            throw new Exception(ex.getMessage()+" => " + query +ex.getMessage());
        }
        return list;
    }
    
    private void completeInsert(Parametro param){
        String query = "call sp_cursorInsertNote('"+param.getTipo()+"',"+param.getTrimestre()+","+param.getIdGrupo()+");";
        try {
            
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            
            Thread.sleep(200);
        } catch (Exception ex) {
            
        }
    }

    @Override
    public int update(Nota param) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("update tblnotas set ");
        query.append("nota="+param.getNota());
        query.append("WHERE id = " + param.getId());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado "+query.toString());
        }
        
        objConexion.desconectar();
        return upd;
    }
    
    
    
}
