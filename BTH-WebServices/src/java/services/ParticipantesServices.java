/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ParticipantesDao;
import dao.ProyectosDao;
import dto.Participantes;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author merxdev
 */
@Path("participante")
public class ParticipantesServices {
    @Path("insert")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Participantes param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ParticipantesDao dao = factory.getNewParticipanteDao();
        

//       
        try {
            dao.insert(param.getCadena());
            
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido");
        }

        return new Gson().toJson(respuesta);
    }
    
    
    @Path("/getListByProyect")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListAssistanceByGroup(Participantes obj) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ParticipantesDao dao = factory.getNewParticipanteDao();
        
        try {
            ArrayList<Participantes> lista = dao.listByProyect(obj);

            respuesta.setSuccess(true);
            respuesta.setMessage("Se a procesado correptamente");
            respuesta.setResponse(lista);
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage()+"------");
            return new Gson().toJson(respuesta);
        }
    }
}
