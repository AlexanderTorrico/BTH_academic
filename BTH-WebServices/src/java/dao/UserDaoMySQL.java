package dao;

import consola.MessageCorreo;
import consola.SendEmail;
import dal.Conexion;
import dto.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEO
 */
public class UserDaoMySQL extends UserDao {

  
    @Override
    public User getLogin(String username, String correo, String contrasenia) {

        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select * from tblcolegios where username = '"+username+ "'or correo='"+correo+"' "
                    + "and contrasenia = hex(aes_encrypt('"+contrasenia+"','COL')) and estado =1 \n" +
"                 ";
  
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                User obj = new User();
                
                String _username = objResultSet.getString("username");
                obj.setUsername(_username);

                String _correo = objResultSet.getString("correo");
                obj.setCorreo(_correo);

                String _contraseña = objResultSet.getString("contrasenia");
                obj.setContrasenia(_contraseña);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }

        return null;

    }

    @Override
    public String insertToken(String correo) {
        
        Conexion objConexion = Conexion.getOrCreate();

        try {
            Date date = new Date();
            
            int a = (int)(Math.random()*10000);
        
            String token = a+"bth"+a;

            byte[] ENCRYPTADO = Base64.getEncoder().encode(token.getBytes());
            String encript = new String(ENCRYPTADO);
            
            StringBuilder query = new StringBuilder("INSERT INTO TBLTOKENS VALUES (");
            query.append("null,");
            query.append("'"+encript+"',");
            query.append("'"+correo+"',");
            query.append("'" + "cc" + "',");
            query.append("'" + "c" + "',");
            query.append("'" + "20200420" + "'");
            query.append(")");
            
            
            
            objConexion.ejecutarInsert(query.toString());
            return encript;
        } catch (Exception e) {
            
            System.out.println("----------------------********************************Error al insertar el token "+e.getMessage());
            
        }
        
        
        objConexion.desconectar();
        
        return "";
    }

    @Override
    public boolean recoveryPassword(User obj) {
        SendEmail enviarCorreo = new SendEmail();
        MessageCorreo correo = new MessageCorreo(obj.getCorreo(), obj.getUsername(), "Confirmar cmabio de contraseña","colegio");
        //enviarCorreo.SendEmail(correo.getCorreo(), correo.getAsunto(),correo.recuverPasswordHtml("colegio", obj.getUsername()));
        enviarCorreo.SendEmail(obj.getCorreo(), correo.getAsunto(), correo.recuverPasswordHtml(obj.getTipo(), obj.getUsername(), obj.getCorreo() ));
        return false;
    }

}
