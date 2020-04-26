/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.MovilInfoEstudianteDao;
import dao.UserDao;
import dto.Movil;
import dto.User;
import factory.FactoryDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author merxdev
 */
@Path("movil")
public class MovilServices {
    //http://localhost:36129/bth/api/movil/info
    @Path("info")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public String login(User param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        MovilInfoEstudianteDao dao = factory.getNewMovilInfoEstudiante();
        try {
            Movil usuario = dao.get(param);
            
            if (usuario!= null) {
                respuesta.setSuccess(true);
                respuesta.setMessage("ingreso correcto");
                respuesta.setResponse(usuario);
                
                return new Gson().toJson(usuario);
            } else {
                respuesta.setMessage("ingreso incorrecto");
            }
        } catch (Exception e) {
            respuesta.setMessage(e.getMessage());
            return new Gson().toJson(respuesta);
        }
        return null;
        
    }
}
