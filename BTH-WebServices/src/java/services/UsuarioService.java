/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.UsuarioDao;
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
@Path("usuario")
public class UsuarioService {

    @Path("prueba")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        Respuesta respuesta = new Respuesta();
        
        respuesta.setSuccess(true);
        respuesta.setMessage("hola mundo");
        
        return new Gson().toJson(respuesta);
    }
    
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(Usuario param) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        
        FactoryDao factory = FactoryDao.getFactoryInstance();
        UsuarioDao dao = factory.getNewUsuarioDao();
        
        Usuario usuario = dao.get(param.getUsername());
        
        if (usuario != null && usuario.getPassword().equals(param.getPassword())) {
            respuesta.setSuccess(true);
            respuesta.setMessage("ingreso correcto");
            respuesta.setResponse(usuario);
        } else {
            respuesta.setMessage("ingreso incorrecto");
        }
        
        return new Gson().toJson(respuesta);
    }
    
    @Path("registro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Usuario param) {
        Respuesta respuesta = new Respuesta();
        
        FactoryDao factory = FactoryDao.getFactoryInstance();
        
        UsuarioDao usuarioDao = factory.getNewUsuarioDao();       
        Usuario usuario = usuarioDao.get(param.getUsername());
        
        // si ya hay un usuario en la bd con ese nombre de usuario
        if (usuario != null) {
            respuesta.setMessage("el nombre ya esta registrado");
            return new Gson().toJson(respuesta);
        }
        
        try {
            int idGenerado = usuarioDao.insert(param);
            param.setUsuarioId(idGenerado);
            
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido, puede que el nombre de usuario este registrado");
        }
        
        return new Gson().toJson(respuesta);
    }
    
}
