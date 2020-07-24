package services;

import com.google.gson.Gson;
import dao.CarreraDao;
import dao.DiasDao;
import dto.Carrera;
import dto.Dias;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author LEO
 */
@Path("dias")
public class DiasServices {

    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Dias obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        DiasDao dao = factory.getNewDiasDao();
        int i = 0;
        try {
            i = dao.update(obj);
            respuesta.setSuccess(true);
            respuesta.setResponse(i);
            if (i == 0) {
                respuesta.setMessage("Sin datos a actualizar");
            } else {
                respuesta.setMessage("Dato actualizado");
            }
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            respuesta.setResponse(i);
            return new Gson().toJson(respuesta);
        }
    }

    @Path("/registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Dias param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        DiasDao dao = factory.getNewDiasDao();
        Dias s = new Dias();
        try {
            int idDias = dao.insert(param);
            param.setId(idDias);
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            respuesta.setResponse(param);
        } catch (Exception ex) {
            respuesta.setMessage("registro fallido");
        }
        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getlistDias(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        DiasDao dias = factory.getNewDiasDao();
        try {
            Dias objDias = dias.get(id);
            if (objDias != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("Dias Obtenida");
                respuesta.setResponse(objDias);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ningun dia");
            }

        } catch (Exception ex) {
            respuesta.setSuccess(true);
            respuesta.setMessage("Ocurrio un error" + ex.getMessage());
        }
        return new Gson().toJson(respuesta);
    }

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        DiasDao dao = factory.getNewDiasDao();

        ArrayList<Dias> dias = dao.getList();

        respuesta.setSuccess(true);
        respuesta.setMessage("lista de Dias");
        respuesta.setResponse(dias);

        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarDias(@PathParam("id") int id) {// nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        DiasDao dao = factory.getNewDiasDao();

        try {
            dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setMessage("Dias eliminado!");
        } catch (Exception ex) {
            respuesta.setMessage("hubo un error al eliminar la Carrera");
        }

        return new Gson().toJson(respuesta);
    }
}
