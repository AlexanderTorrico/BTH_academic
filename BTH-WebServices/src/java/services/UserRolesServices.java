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
import javax.ws.rs.Consumes;
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

    @Path("/saveDocente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(UserRoles obj) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        UserRolesDao dao = factory.getNewUserRolesDao();
        int i = 0;
        try {
            dao.save(obj);
            
            respuesta.setSuccess(true);
            respuesta.setResponse(i);
            if (i == 0) {
                respuesta.setMessage("Sin datos a actualizar");
            } else {
                respuesta.setMessage("Dato actualizado");
            }
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            respuesta.setResponse(i);
            return new Gson().toJson(respuesta);
        }
    }
    
    
    @Path("/getIddocente/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getidDocente(UserRoles param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        UserRolesDao dao = factory.getNewUserRolesDao();

        try {
            ArrayList<UserRoles> parametro = dao.getIdDocente(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
    
    @Path("/exist/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String exist() {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        UserRolesDao dao = factory.getNewUserRolesDao();

        try {
            ArrayList<UserRoles> parametro = dao.existUserRol();
            
            if(parametro.size()==0){
                respuesta.setMessage("Sin usuarios");
            }else{
                respuesta.setSuccess(true);
                respuesta.setMessage("Existen "+parametro.size()+" usuarios con roles");
            }
            
            //respuesta.setResponse(parametro);

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
    @Path("/convertAdmBTH")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String convertAdmBTH(UserRoles obj) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        UserRolesDao dao = factory.getNewUserRolesDao();
        int i = 0;
        try {
            dao.convertInBTHAdmin(obj);
            
            respuesta.setSuccess(true);
            respuesta.setResponse(i);
            if (i == 0) {
                respuesta.setMessage("Sin datos a actualizar");
            } else {
                respuesta.setMessage("Dato actualizado");
            }
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            respuesta.setResponse(i);
            return new Gson().toJson(respuesta);
        }
    }
}
