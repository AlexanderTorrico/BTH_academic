/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ColegioDao;
import dao.DocenteDao;
import dao.UsuarioDao;
import dto.Colegio;
import dto.Docente;
import dto.Usuario;
import factory.FactoryDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
            if (docente.getCorreo().equals(param.getCorreo()) && docente.getUsername().equals(param.getUsername())) {
                respuesta.setMessage("el docente ya esta registrado");
                return new Gson().toJson(respuesta);
            } else if (docente.getCorreo().equals(param.getCorreo())) {
                respuesta.setMessage("el correo ya fue utilizado");
                return new Gson().toJson(respuesta);
            } else if (docente.getUsername().equals(param.getUsername())) {
                respuesta.setMessage("el username ya fue utilizado");
                return new Gson().toJson(respuesta);
            }
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

    @Path("verificar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String verificar(Docente param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        DocenteDao dao = factory.getNewDocenteDao();
        Docente docente = dao.get(param.getUsername(), param.getCorreo());

        // si ya hay un usuario en la bd con ese nombre de usuario
        if (docente != null) {
            if (docente.getCorreo().equals(param.getCorreo()) && docente.getUsername().equals(param.getUsername())) {
                respuesta.setMessage("el docente ya esta registrado");
                return new Gson().toJson(respuesta);
            } else if (docente.getCorreo().equals(param.getCorreo())) {
                respuesta.setMessage("el correo ya fue utilizado");
                return new Gson().toJson(respuesta);
            } else if (docente.getUsername().equals(param.getUsername())) {
                respuesta.setMessage("el username ya fue utilizado");
                return new Gson().toJson(respuesta);
            }
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
}
// LOGIN AUTENTICACION
