/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.GruposDao;
import dto.Grupos;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author LEO
 */
@Path("grupos")
public class GruposServices {

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Grupos param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GruposDao dao = factory.getNewGruposDao();
        Grupos s = new Grupos();
        s.setNivel(param.getNivel());
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

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GruposDao dao = factory.getNewGruposDao();

        ArrayList<Grupos> grupos = dao.getList();

        respuesta.setSuccess(true);
        respuesta.setMessage("lista de Grupos");
        respuesta.setResponse(grupos);

        return new Gson().toJson(respuesta);
    }
    
     @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getlistPorGrupos(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        GruposDao grupos = factory.getNewGruposDao();
        try {
            Grupos objCarrera = grupos.get(id);
            if (objCarrera != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("Grupos Obtenida");
                respuesta.setResponse(objCarrera);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ningun Grupo");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("Ocurrio un error" + ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }
    
   
    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Grupos obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        GruposDao dao = factory.getNewGruposDao();
        int i = 0;
        try {
            i = dao.update(obj);
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

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarGrupos(@PathParam("id") int id) {// nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GruposDao dao = factory.getNewGruposDao();

        try {
            dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setMessage("Grupo eliminad!");
        } catch (Exception ex) {
            respuesta.setMessage("hubo un error al eliminar el Grupo");
        }

        return new Gson().toJson(respuesta);
    }

}
