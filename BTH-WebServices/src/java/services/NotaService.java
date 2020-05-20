/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.NotaDao;
import dao.ParametroDao;
import dto.Nota;
import dto.NotaTrimestral;
import dto.Parametro;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author merxdev
 */
@Path("nota")
public class NotaService {
    
    @Path("/getList")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getList(Parametro param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        NotaDao dao = factory.getNewNotaeDao();

        try {
            ArrayList<Nota> parametro = dao.get(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
    
    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(ArrayList<Nota> obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        NotaDao dao = factory.getNewNotaeDao();
        int i = 0;
        try {
            for(Nota ob: obj){
                dao.update(ob);
            }
            
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
    
    @Path("/getListII")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListII(Parametro param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        NotaDao dao = factory.getNewNotaeDao();

        try {
            ArrayList<Nota> parametro = dao.getII(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
    @Path("/notatrimestral")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getNotaTrimestral(NotaTrimestral param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        NotaDao dao = factory.getNewNotaeDao();

        try {
            ArrayList<NotaTrimestral> parametro = dao.notaTrimestral(param);

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
