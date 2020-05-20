/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.GrupoDao;
import dao.ParametroDao;
import dto.InformacionesGrupo;
import dto.Parametro;
import dto.infoGrupos;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author merxdev
 */
@Path("grupo")
public class GrupoServices {
    
    
    @Path("/grupoInfo/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getList(infoGrupos param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GrupoDao dao = factory.getNewGrupoDao();

        try {
            ArrayList<infoGrupos> parametro = dao.grupoInfo(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
    @Path("/grupo/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getList(InformacionesGrupo param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GrupoDao dao = factory.getNewGrupoDao();

        try {
            ArrayList<InformacionesGrupo> parametro = dao.docenteWebGrupos(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
    @Path("/horario/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListHorario(InformacionesGrupo param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GrupoDao dao = factory.getNewGrupoDao();

        try {
            ArrayList<InformacionesGrupo> parametro = dao.docenteWebHorario(param);

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
