/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ColegioDao;
import dto.CarrerasHabilitadasColegio;
import dto.Colegio;
import factory.FactoryDao;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Marcelo Villca
 */
@Path("colegio")
public class ColegioService {

    @Path("registro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Colegio param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        ColegioDao dao = factory.getNewColegioDao();
        Colegio colegio = dao.get(param.getUsername(), param.getCorreo());

        // si ya hay un usuario en la bd con ese nombre de usuario
        if (colegio != null) {
            if (colegio.getCorreo().equals(param.getCorreo()) && colegio.getUsername().equals(param.getUsername())) {
                respuesta.setMessage("el colegio ya esta registrado");
                return new Gson().toJson(respuesta);
            } else if (colegio.getCorreo().equals(param.getCorreo())) {
                respuesta.setMessage("el correo ya fue utilizado");
                return new Gson().toJson(respuesta);
            } else if (colegio.getUsername().equals(param.getUsername())) {
                respuesta.setMessage("el username ya fue utilizado");
                return new Gson().toJson(respuesta);
            }
        }

        if (param.getNombre() == "") {
            respuesta.setMessage("Falta el nombre");
            return new Gson().toJson(respuesta);
        }
        if (param.getSigep() == "") {
            respuesta.setMessage("Falta Completar ApellidoPaterno");
            return new Gson().toJson(respuesta);
        }
        if (param.getDirector() == "") {
            respuesta.setMessage("Falta Completar ApellidoMaterno");
            return new Gson().toJson(respuesta);
        }
        if (param.getDireccion() == "") {
            respuesta.setMessage("Falta Completar Direccion");
            return new Gson().toJson(respuesta);
        }
        if (param.getTelefono() == "") {
            respuesta.setMessage("Falta Completar Telefono");
            return new Gson().toJson(respuesta);
        }
        if (param.getCorreo() == "") {
            respuesta.setMessage("Falta Completar Correo");
            return new Gson().toJson(respuesta);
        }
        if (param.getUsername() == "") {
            respuesta.setMessage("Falta Completar Username");
            return new Gson().toJson(respuesta);
        }

        try {
            int idGenerado = dao.insert(param);
            param.setId(idGenerado);
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            respuesta.setResponse(param);
            dao.ValidarCuenta(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido");
        }

        return new Gson().toJson(respuesta);
    }

    @Path("/{token}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String obtener(@PathParam("token") String tokenid) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();
        byte[] decodedBytes = Base64.getDecoder().decode(tokenid);
        String desencriptado = new String(decodedBytes);

        try {
            dao.ActivarCuenta(desencriptado);
            respuesta.setSuccess(true);
            respuesta.setMessage("cancion modificada!");
        } catch (Exception ex) {
            respuesta.setMessage("ascascs");
        }

        return new Gson().toJson(respuesta);
    }

    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Colegio obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();
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

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public String listar() {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();

        ArrayList<Colegio> carrera = dao.getList();

        respuesta.setSuccess(true);
        respuesta.setMessage("lista de colegio");
        respuesta.setResponse(carrera);

        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarColegio(@PathParam("id") int id) {// nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();

        try {
            dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setMessage("Colegio eliminada!");
        } catch (Exception ex) {
            respuesta.setMessage("hubo un error al eliminar el colegio");
        }

        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getColegio(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        ColegioDao carrera = factory.getNewColegioDao();
        try {
            Colegio ob = carrera.get(id);
            if (ob != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("COLEGIO Obtenida");
                respuesta.setResponse(ob);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ninguna COLEGIO");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("Ocurrio un error" + ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }

    @Path("listacarreras/habilitadas/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaCarrerasHabilitadas(@PathParam("id") int idColegio) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();
        ArrayList<CarrerasHabilitadasColegio> reportes = dao.getListCarrerasHabilitadas(idColegio);
        respuesta.setSuccess(true);
        respuesta.setMessage("4");
        respuesta.setResponse(reportes);
        return new Gson().toJson(respuesta);
    }
}
