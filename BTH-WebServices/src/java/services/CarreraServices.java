package services;

import com.google.gson.Gson;
import dao.CarreraDao;
import dao.DocenteDao;
import dto.Carrera;
import dto.Docente;
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
@Path("carrera")
public class CarreraServices {

    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Carrera obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        CarreraDao dao = factory.getNewCarreraDao();
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
    public String registro(Carrera param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        CarreraDao dao = factory.getNewCarreraDao();
        Carrera s = new Carrera();
        s.setNombre(param.getNombre());

        if (param.getNombre() == "") {
            respuesta.setMessage("Falta el nombre");
            return new Gson().toJson(respuesta);
        }
        if (param.getSigla()== "") {
            respuesta.setMessage("Falta sigla");
            return new Gson().toJson(respuesta);
        }
        
        if (param.getDescripcion()== "") {
            respuesta.setMessage("Falta descripcion");
            return new Gson().toJson(respuesta);
        }
        if (param.getImg()== "") {
            respuesta.setMessage("Falta tipo");
            return new Gson().toJson(respuesta);
        }
        

        try {
            int idGenerado = dao.insert(param);
            param.setId(idGenerado);
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
    public String getlistPorCarrera(@PathParam("id") int id) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();

        CarreraDao carrera = factory.getNewCarreraDao();
        try {
            Carrera objCarrera = carrera.get(id);
            if (objCarrera != null) {

                respuesta.setSuccess(true);
                respuesta.setMessage("Carrera Obtenida");
                respuesta.setResponse(objCarrera);
                return new Gson().toJson(respuesta);

            } else {
                respuesta.setMessage("no se obtenio ninguna Carrera");
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
        CarreraDao dao = factory.getNewCarreraDao();

        ArrayList<Carrera> carrera = dao.getList();

        respuesta.setSuccess(true);
        respuesta.setMessage("lista de carrera");
        respuesta.setResponse(carrera);

        return new Gson().toJson(respuesta);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarCarrera(@PathParam("id") int id) {// nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        CarreraDao dao = factory.getNewCarreraDao();

        try {
            dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setMessage("Carrera eliminada!");
        } catch (Exception ex) {
            respuesta.setMessage("hubo un error al eliminar la Carrera");
        }

        return new Gson().toJson(respuesta);
    }
}
