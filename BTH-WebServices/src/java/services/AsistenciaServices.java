/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.AsistenciaDao;
import dao.CarreraDao;
import dto.Asistencia;
import dto.Carrera;
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
@Path("asistencia")
public class AsistenciaServices {

    @Path("/getDateByGroup")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getDateByGroup(Asistencia obj) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        AsistenciaDao dao = factory.getNewAsistenciaDao();
        
        try {
            ArrayList<Asistencia> lista = dao.getDateByGroup(obj.getId());

            respuesta.setSuccess(true);
            respuesta.setMessage("Se a procesado correptamente");
            respuesta.setResponse(lista);
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
    
    @Path("/getListAssistanceByGroup")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListAssistanceByGroup(Asistencia obj) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        AsistenciaDao dao = factory.getNewAsistenciaDao();
        
        try {
            ArrayList<Asistencia> lista = dao.getlistAssistance(obj);

            respuesta.setSuccess(true);
            respuesta.setMessage("Se a procesado correptamente"+obj.getFecha());
            respuesta.setResponse(lista);
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage()+"------");
            return new Gson().toJson(respuesta);
        }
    }
    
    @Path("/getListFinal")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListFinal(Asistencia obj) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        AsistenciaDao dao = factory.getNewAsistenciaDao();
        
        try {
            ArrayList<Asistencia> lista = dao.listAssistanceCount(obj);

            respuesta.setSuccess(true);
            respuesta.setMessage("Se a procesado correptamente"+obj.getFecha());
            respuesta.setResponse(lista);
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage()+"------");
            return new Gson().toJson(respuesta);
        }
    }
    
    
    @Path("insertByGroup")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Asistencia param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        AsistenciaDao dao = factory.getNewAsistenciaDao();
        
        
        try {
            int idGenerado = dao.insertAsistenciaByGroup(param);
            
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }
    
    @Path("update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Asistencia param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        AsistenciaDao dao = factory.getNewAsistenciaDao();
        
        try {
            int idGenerado = dao.updateAssistance(param);
            
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido");
        }
        return new Gson().toJson(respuesta);
    }
}
