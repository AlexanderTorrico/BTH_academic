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
import dto.PlanPagos;
import dto.User;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @Path("/mesAPagar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListMesAPagar(Pago param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        PagoDao dao = factory.getNewPagoDao();

        try {
            ArrayList<Pago> parametro = dao.getMesFaltanteAPagar(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(parametro);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }

    @Path("/{ci}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String obtener(@PathParam("ci") String ci) throws Exception {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        PagoDao dao = factory.getNewPagoDao();
        ArrayList<PlanPagos> parametro = dao.getPlanPagos(ci);
        respuesta.setSuccess(true);
        respuesta.setMessage("el estudiante");
        respuesta.setResponse(parametro);
        return new Gson().toJson(respuesta);
    }
      @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Pago param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        PagoDao dao = factory.getNewPagoDao();
       
        try {
            int idGenerado = dao.insert(param);
            param.setId(idGenerado);
            respuesta.setSuccess(true);
            respuesta.setMessage("pago exitoso");
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("pago fallido");
        }

        return new Gson().toJson(respuesta);
    }

}
