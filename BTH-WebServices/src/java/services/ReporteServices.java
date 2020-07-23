/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ColegioDao;
import dao.PagoDao;
import dao.ParametroDao;
import dto.ColegioReporte;
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
@Path("reporte")
public class ReporteServices {

    @Path("carrera/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaReportesCarrera(@PathParam("id") int idColegio) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();
        ArrayList<ColegioReporte> reportes = dao.getListReportesCarrera(idColegio);
        respuesta.setSuccess(true);
        respuesta.setMessage("1");
        respuesta.setResponse(reportes);
        return new Gson().toJson(respuesta);
    }

    @Path("grupo/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaReportesGrupo(@PathParam("id") int idColegio) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();
        ArrayList<ColegioReporte> reportes = dao.getListReportesGrupo(idColegio);
        respuesta.setSuccess(true);
        respuesta.setMessage("2");
        respuesta.setResponse(reportes);
        return new Gson().toJson(respuesta);
    }

    @Path("nota/carrera/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaNotasCarrera(@PathParam("id") int idColegio) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();
        ArrayList<ColegioReporte> reportes = dao.getListReportesNotasCarrera(idColegio);
        respuesta.setSuccess(true);
        respuesta.setMessage("3");
        respuesta.setResponse(reportes);
        return new Gson().toJson(respuesta);
    }
    @Path("nota/grupo/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaNotasGrupo(@PathParam("id") int idColegio) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ColegioDao dao = factory.getNewColegioDao();
        ArrayList<ColegioReporte> reportes = dao.getListReportesNotasCarrera(idColegio);
        respuesta.setSuccess(true);
        respuesta.setMessage("4");
        respuesta.setResponse(reportes);
        return new Gson().toJson(respuesta);
    }
}
