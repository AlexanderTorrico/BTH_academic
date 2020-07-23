/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Grupos;
import dto.UserRoles;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author merxdev
 */
public class UserRolesDaoMysql extends UserRolesDao{

    @Override
    public ArrayList<UserRoles> getList(UserRoles param) throws Exception {
        ArrayList<UserRoles> lista = new ArrayList<UserRoles>();

        String query = "select a.*, c.nombre as colegio from (select * from tblusuariosroles where idUsuario = "+param.getId()+")a " +
            "	left join tblcolegios c on a.idReference=c.id;";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                UserRoles obj = new UserRoles();

                obj.setId(objResultSet.getInt("id"));
                obj.setIdReferencia(objResultSet.getInt("idReference"));
                
                obj.setIdRol(objResultSet.getInt("idRol"));
                
                
                obj.setEstado(objResultSet.getBoolean("estado"));
                
                obj.setColegio(objResultSet.getString("colegio"));
                

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
    
}
