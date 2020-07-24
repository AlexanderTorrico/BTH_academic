/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ColegioDao;
import dao.DocenteDao;
import dto.Colegio;
import dto.Docente;
import factory.FactoryDao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Base64;
import javax.ws.rs.DELETE;

/**
 *
 * @author Marcelo Villca
 */
@Path("docente")
public class DocenteService {

    @Path("registro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Docente param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        DocenteDao dao = factory.getNewDocenteDao();
        Docente docente = dao.get(param.getUsername(), param.getCorreo());

//         si ya hay un usuario en la bd con ese nombre de usuario
        if (docente != null) {
            if (docente.getCorreo().equals(param.getCorreo())) {
                respuesta.setMessage("el correo ya fue utilizado");
                return new Gson().toJson(respuesta);
            } else if (docente.getUsername().equals(param.getUsername())) {
                respuesta.setMessage("el username ya fue utilizado");
                return new Gson().toJson(respuesta);
            }
        }
        if (param.getNombre() == "") {
            respuesta.setMessage("Falta el nombre");
            return new Gson().toJson(respuesta);
        }
        if (param.getApaterno() == "") {
            respuesta.setMessage("Falta Completar Apellido Paterno");
            return new Gson().toJson(respuesta);
        }
        if (param.getAmaterno() == "") {
            respuesta.setMessage("Falta Completar Apellido Materno");
            return new Gson().toJson(respuesta);
        }
        if (param.getCorreo() == "") {
            respuesta.setMessage("Falto Completar Correo");
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
            dao.ValidarCuenta(param);
            respuesta.setResponse(param);
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
        DocenteDao dao = factory.getNewDocenteDao();
        byte[] decodedBytes = Base64.getDecoder().decode(tokenid);
        String desencriptado = new String(decodedBytes);

        try {
            dao.ActivarCuenta(desencriptado);
            respuesta.setSuccess(true);
            respuesta.setMessage("cancion modificada!");
        } catch (Exception ex) {
            respuesta.setMessage("hubo un error al actualizar " + ex.getMessage());
        }

        return new Gson().toJson(respuesta);
    }

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        DocenteDao dao = factory.getNewDocenteDao();

        ArrayList<Docente> docente = dao.getList();

        respuesta.setSuccess(true);
        respuesta.setMessage("lista de docentes");
        respuesta.setResponse(docente);

        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarDocente(@PathParam("id") int id) {// nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        DocenteDao dao = factory.getNewDocenteDao();

        try {
            dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setMessage("Docente eliminado!");
        } catch (Exception ex) {
            respuesta.setMessage("hubo un error al eliminar la Carrera");
        }

        return new Gson().toJson(respuesta);
    }
}

// LOGIN AUTENTICACION
