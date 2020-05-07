/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ColegioDao;
import dao.ParametroDao;
import dao.UserDao;
import dao.indexInformacionDao;
import dto.Carrera;
import dto.Colegio;
import dto.InformacionesCarrera;
import dto.Parametro;
import dto.User;
import factory.FactoryDao;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author carlos clavijo
 */
@Path("index")
public class index {

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIndex() {

        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        indexInformacionDao daoIndex = factory.getindexInformacionDao();
        try {
            ArrayList<Carrera> carreras = daoIndex.getListIndex();
            if (carreras != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("lista obtenida");
                respuesta.setResponse(carreras);
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
    public String getInformacion(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        indexInformacionDao daoIndex = factory.getindexInformacionDao();
        try {
            ArrayList<InformacionesCarrera> informaciones = daoIndex.getListInformacion(id);
            if (informaciones != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("lista obtenida");
                respuesta.setResponse(informaciones);
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
}
