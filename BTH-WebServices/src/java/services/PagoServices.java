/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.PagoDao;
import dao.ParametroDao;
import dto.MovilTrimestre;
import dto.Pago;
import dto.Parametro;
import dto.User;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author merxdev
 */
@Path("pago")
public class PagoServices {
    
    @Path("/byGroup")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListByGroup(User param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        PagoDao dao = factory.getNewPagoDao();

        try {
            ArrayList<MovilTrimestre> parametro = dao.get(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(parametro);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
}
