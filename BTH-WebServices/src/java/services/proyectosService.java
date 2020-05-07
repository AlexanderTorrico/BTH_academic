/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ParametroDao;
import dao.ProyectosDao;
import dao.ProyectosDaoMySQL;
import dao.indexInformacionDao;
import dto.Carrera;
import dto.InformacionesCarrera;
import dto.Parametro;
import dto.Proyectos;
import dto.User;
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
 * @author carlos clavijo
 */
@Path("proyectos")
public class proyectosService {

    @Path("/carrera/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProyectosAll() {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        ProyectosDao daoIndex = factory.getProyectosDao();
        try {
            ArrayList<Proyectos> proyectos = daoIndex.getList();
            if (proyectos != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("lista obtenida");
                respuesta.setResponse(proyectos);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ninguna lista");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("listado fallido" + ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }

    @Path("/carrera/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getlistPorCarrera(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        ProyectosDao daoIndex = factory.getProyectosDao();
        try {
            ArrayList<Proyectos> proyectos = daoIndex.getListPorCarrera(id);
            if (proyectos != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("lista obtenida");
                respuesta.setResponse(proyectos);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ninguna lista");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("listado fallido" + ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String get(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        ProyectosDao daoIndex = factory.getProyectosDao();
        try {
            Proyectos proyectos = daoIndex.get(id);
            if (proyectos != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("lista obtenida");
                respuesta.setResponse(proyectos);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ninguna lista");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("listado fallido" + ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarCancion(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();

        ProyectosDao dao = factory.getProyectosDao();
        int i = 0;
        try {
            i = dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setResponse(i);
            if (i == 0) {
                respuesta.setMessage("Sin datos a eliminar");
            } else {
                respuesta.setMessage("Dato eliminado");
            }
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            respuesta.setResponse(i);
            return new Gson().toJson(respuesta);
        }
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Proyectos param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ProyectosDao dao = factory.getProyectosDao();

        try {
            int idGenerado = dao.insert(param);
            param.setId(idGenerado);
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }

    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Proyectos obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ProyectosDao dao = factory.getProyectosDao();
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
}
