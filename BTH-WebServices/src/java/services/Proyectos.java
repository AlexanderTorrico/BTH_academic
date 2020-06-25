/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.DocenteDao;
import dao.ProyectosDao;
import dto.Docente;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author user
 */
@Path("proyect")
public class Proyectos {
    @Path("insert2")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(dto.Proyectos param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ProyectosDao dao = factory.getProyectosDao();
        

//       
        try {
            int idGenerado = dao.insert(param);
            param.setId(idGenerado);
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido");
        }

        return new Gson().toJson(respuesta);
    }
    
    @Path("/getListByGroup")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListAssistanceByGroup(dto.Proyectos obj) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ProyectosDao dao = factory.getProyectosDao();
        
        try {
            ArrayList<dto.Proyectos> lista = dao.getListByGroup(obj);

            respuesta.setSuccess(true);
            respuesta.setMessage("Se a procesado correptamente");
            respuesta.setResponse(lista);
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage()+"------");
            return new Gson().toJson(respuesta);
        }
    }
    
    @Path("delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String delete(dto.Proyectos param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ProyectosDao dao = factory.getProyectosDao();
         
        try {
            String dato = dao.deleteProyectAndPartaker(param);
            
            respuesta.setSuccess(true);
            respuesta.setMessage(dato);
            
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido");
        }

        return new Gson().toJson(respuesta);
    }
    
    
    @Path("active")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(dto.Proyectos param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ProyectosDao dao = factory.getProyectosDao();
         
        try {
            String dato = dao.isActive(param);
            
            respuesta.setSuccess(true);
            respuesta.setMessage(dato);
            
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido");
        }

        return new Gson().toJson(respuesta);
    }
    
    @Path("/getListByGroupEstado")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListAssistanceByGroupEstado(dto.Proyectos obj) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ProyectosDao dao = factory.getProyectosDao();
        
        try {
            ArrayList<dto.Proyectos> lista = dao.getListByGroupEstado(obj);

            respuesta.setSuccess(true);
            respuesta.setMessage("Se a procesado correptamente");
            respuesta.setResponse(lista);
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage()+"------");
            return new Gson().toJson(respuesta);
        }
    }
}
