package services;

import com.google.gson.Gson;
import dao.CarreraDao;
import dao.ReportesPagosDao;
import dto.Carrera;
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
 * @author LEO
 */
@Path("reportesPago")
public class ReportesPagos {

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getlistPorCarrera(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        CarreraDao carrera = factory.getNewCarreraDao();
        try {
            Carrera objCarrera = carrera.get(id);
            if (objCarrera != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("Reporte Obtenida");
                respuesta.setResponse(objCarrera);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ningun registro");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("Ocurrio un error" + ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String listar(dto.ReportesPagos objReporte) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ReportesPagosDao dao = factory.getNewReportesPagosDao();

        ArrayList<dto.ReportesPagos> pagos = dao.getList(objReporte);

        respuesta.setSuccess(true);
        respuesta.setMessage("lista de Reportes Pagos");
        respuesta.setResponse(pagos);

        return new Gson().toJson(respuesta);
    }
}
