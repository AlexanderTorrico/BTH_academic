/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ColegioDao;
import dao.DocenteDao;
import dao.EstudianteDao;
import dto.Colegio;
import dto.Docente;
import dto.Estudiante;
import factory.FactoryDao;
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

/**
 *
 * @author Marcelo Villca
 */
@Path("estudiante")
public class EstudianteService {

    @Path("registro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Estudiante param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        EstudianteDao dao = factory.getNewEstudianteDao();

//        Estudiante estudiante = dao.get(param.getCi());
//
////         si ya hay un usuario en la bd con ese nombre de usuario
//        if (estudiante != null) {       
//                respuesta.setMessage("el estudiante ya esta registrado");
//                return new Gson().toJson(respuesta);           
//        }
        try {
            int idGenerado = dao.insert(param);
            param.setId(idGenerado);
            respuesta.setSuccess(true);
            dao.insertAlgrupo(param.getId(), param.getIdGrupo());
            respuesta.setMessage("registro exitoso");
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido");
        }

        return new Gson().toJson(respuesta);
    }
}
// LOGIN AUTENTICACION
