/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dto.Gestion;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import dao.GestionDao;
import dao.GestionDaoMySQL;

/**
 *
 * @author LEO
 */
@Path("gestiones")
public class GestionServices {

    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Gestion obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        GestionDao dao = factory.getNewGestionDao();
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

    @Path("/registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Gestion param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GestionDao dao = factory.getNewGestionDao();
        Gestion s = new Gestion();
        s.setMesAPagar(param.getMesAPagar());

        if (param.getMesAPagar()== 0) {
            respuesta.setMessage("Falta el nombre");
            return new Gson().toJson(respuesta);
        }

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

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getlistPorGestion(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        GestionDao gestion = factory.getNewGestionDao();
        try {
            Gestion objGestion = gestion.get(id);
            if (objGestion != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("Gestion Obtenida");
                respuesta.setResponse(objGestion);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ninguna gestion");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("Ocurrio un error" + ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GestionDao dao = factory.getNewGestionDao();

        ArrayList<Gestion> carrera = dao.getList();

        respuesta.setSuccess(true);
        respuesta.setMessage("lista de Gestion");
        respuesta.setResponse(carrera);

        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarGestion(@PathParam("id") int id) {// nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        GestionDao dao = factory.getNewGestionDao();

        try {
            dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setMessage("Gestion eliminada!");
        } catch (Exception ex) {
            respuesta.setMessage("hubo un error al eliminar la gestion");
        }

        return new Gson().toJson(respuesta);
    }
}
