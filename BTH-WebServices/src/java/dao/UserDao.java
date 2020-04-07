package dao;

import dto.User;
import java.util.ArrayList;

public abstract class UserDao {

    public abstract User getLogin(String username , String correo , String contrasenia);
    
    public abstract String insertToken(String correo);
    public abstract boolean recoveryPassword(User obj);
    
    
    

}


