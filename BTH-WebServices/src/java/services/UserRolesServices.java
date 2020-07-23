/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.GrupoDao;
import dao.UserRolesDao;
import dto.UserRoles;
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
@Path("user-roles")
public class UserRolesServices {
    @Path("/get/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getList(UserRoles param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        UserRolesDao dao = factory.getNewUserRolesDao();

        try {
            ArrayList<UserRoles> parametro = dao.getList(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
}
