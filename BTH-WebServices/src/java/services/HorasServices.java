package services;

import com.google.gson.Gson;
import dao.HorasDao;
import dto.Horas;
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
@Path("horas")
public class HorasServices {

    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Horas obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        HorasDao dao = factory.getNewHorasDao();
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
    public String registro(Horas param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        HorasDao dao = factory.getNewHorasDao();
        Horas s = new Horas();
        try {
            int idHoras = dao.insert(param);
            param.setId(idHoras);
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
    public String getlistHoras(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        HorasDao horas = factory.getNewHorasDao();
        try {
            Horas objHoras = horas.get(id);
            if (objHoras != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("Horas Obtenida");
                respuesta.setResponse(objHoras);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ninguna Hora");
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
        HorasDao dao = factory.getNewHorasDao();

        ArrayList<Horas> horas = dao.getList();

        respuesta.setSuccess(true);
        respuesta.setMessage("lista de Horas");
        respuesta.setResponse(horas);

        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarHoras(@PathParam("id") int id) {// nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        HorasDao dao = factory.getNewHorasDao();

        try {
            dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setMessage("Horas eliminado!");
        } catch (Exception ex) {
            respuesta.setMessage("hubo un error al eliminar las Horas");
        }

        return new Gson().toJson(respuesta);
    }
}
