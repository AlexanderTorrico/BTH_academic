package dao;

import dal.Conexion;
import dto.User;
import dto.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;

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

}
