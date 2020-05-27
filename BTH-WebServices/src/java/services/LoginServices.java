/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.GrupoDao;
import dao.LoginDao;
import dto.InformacionesGrupo;
import dto.Login;
import dto.infoGrupos;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author merxdev
 */
@Path("login")
public class LoginServices {
    @Path("/docente/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getList(Login param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        LoginDao dao = factory.getNewLoginDao();

        try {
            Login parametro = dao.docente(param);
            if(parametro.getId()!=0){
                respuesta.setSuccess(true);
                respuesta.setMessage("lista obtenida");
                respuesta.setResponse(parametro);
            }
            

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }

}
