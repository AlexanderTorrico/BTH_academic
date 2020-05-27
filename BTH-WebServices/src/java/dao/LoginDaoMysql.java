/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.Docente;
import dto.Login;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public class LoginDaoMysql extends LoginDao{

    @Override
    public Login docente(Login obj) throws Exception {
        String encript = "";
        String query = "select * from tbldocentes "
                    + "where username='" + obj.getUsername() + "' and "
                + "contrasenia = hex(aes_encrypt('"+obj.getPassword()+"','DOC')) and estado =1;";
        try {
            Conexion objConexion = Conexion.getOrCreate();
            
            ResultSet objResultSet = objConexion.ejecutar(query);
            Login login = new Login();
            if (objResultSet.next()) {
                
                int idDocente = objResultSet.getInt("id");
                login.setId(idDocente);
                
                String nombre = objResultSet.getString("nombre")+" "+objResultSet.getString("apaterno")+" "+objResultSet.getString("amaterno");
                
                login.setEstado(objResultSet.getInt("estado"));
                login.setNombreCompleto(nombre);
            }
            
            return login;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage()+" " + query);
        }
        
    }
    
}
