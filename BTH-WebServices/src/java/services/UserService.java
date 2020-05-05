/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ColegioDao;
import dao.UserDao;
import dto.Colegio;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dto.User;
import factory.FactoryDao;
import javax.ws.rs.GET;

/**
 *
 * @author LEO
 */
@Path("user")
public class UserService {

    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public String login(User param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        UserDao dao = factory.getNewUserDao();
        System.out.print(param);
        User usuario = dao.getLogin(param.getUsername(), param.getUsername(),param.getContrasenia());

        if (usuario!= null && usuario.getContrasenia() != null) {
            respuesta.setSuccess(true);
            respuesta.setMessage("ingreso correcto");
            respuesta.setResponse(usuario);
        } else {
            respuesta.setMessage("ingreso incorrecto");
        }

        return new Gson().toJson(respuesta);
    }

    @Path("recuperar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(User param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        ColegioDao daoColegio = factory.getNewColegioDao();
        Colegio colegio = daoColegio.get("", param.getCorreo()); //

        UserDao dao = factory.getNewUserDao();
        User n = new User();
        n.setCorreo(param.getCorreo());
        try {
            if (colegio != null) {
                param.setUsername(dao.insertToken(param.getCorreo()));

                dao.recoveryPassword(param);
                respuesta.setSuccess(true);
                respuesta.setMessage("Se envieo el enlace del cambio e contraseña a su correo");
            } else {
                respuesta.setMessage("El correo electonico no existe");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("registro fallido" + ex.getMessage());
        }

        return new Gson().toJson(respuesta);
    }

    @Path("cambiarPass")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String cambiarContraseña(User param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        UserDao dao = factory.getNewUserDao();
        User n = new User();
        n.setCorreo(param.getCorreo());
        try {
            dao.cambiarConstraseña(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("Se cambio la contraseña correptamente");

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("registro fallido" + ex.getMessage());
        }

        return new Gson().toJson(respuesta);
    }

}
