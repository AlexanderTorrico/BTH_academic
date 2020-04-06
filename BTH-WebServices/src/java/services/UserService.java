/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.UserDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dto.User;
import factory.FactoryDao;
/**
 *
 * @author LEO
 */

@Path("user")
public class UserService {
    
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public String login(User param) { 
        Respuesta respuesta = new Respuesta();
        
        FactoryDao factory = FactoryDao.getFactoryInstance();
        UserDao dao = factory.getNewUserDao();
        
        User usuario = dao.getLogin(param.getUsername(),param.getUsername(),param.getContrasenia());
        
        if (usuario != null) {
            respuesta.setSuccess(true);
            respuesta.setMessage("ingreso correcto");
            respuesta.setResponse(usuario);
        } else {
            respuesta.setMessage("ingreso incorrecto");
        }
        
        return new Gson().toJson(respuesta);
    }
    
    
    
    
    
}
