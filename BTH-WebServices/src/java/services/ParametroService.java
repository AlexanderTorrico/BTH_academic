/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import dao.ParametroDao;
import dto.Parametro;
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
 * @author Derxal
 */
@Path("parametro")
public class ParametroService {

    @Path("/i")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registro(Parametro param) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ParametroDao dao = factory.getNewParametroDao();

        if (param.getNombre() == null) {
            respuesta.setMessage("Falta el nombre");
            return new Gson().toJson(respuesta);
        }
        if(param.getTipo() == null) {
            respuesta.setMessage("Falta el tipo");
            return new Gson().toJson(respuesta);
        }

        try {
            int idGenerado = dao.insert(param);
            param.setId(idGenerado);
            respuesta.setSuccess(true);
            respuesta.setMessage("registro exitoso");
            respuesta.setResponse(param);

        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
        }

        return new Gson().toJson(respuesta);
    }

    @Path("/gl")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getList() {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ParametroDao dao = factory.getNewParametroDao();

        try {
            ArrayList<Parametro> parametro = dao.getList();

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);

            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    //  http://localhost:43169/bth/api/parametro/gbg/2
    @Path("/gbg")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getByGroup(Parametro param) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ParametroDao dao = factory.getNewParametroDao();

        try {
            ArrayList<Parametro> parametro = dao.getByGrupo(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);
            
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
    
    @Path("eliminar/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String eliminarCancion(@PathParam("id") int id) {
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ParametroDao dao = factory.getNewParametroDao();
        int i = 0;
        try {
            i = dao.delete(id);
            respuesta.setSuccess(true);
            respuesta.setResponse(i);
            if (i == 0) {
                respuesta.setMessage("Sin datos a eliminar");
            } else {
                respuesta.setMessage("Dato eliminado");
            }
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            respuesta.setResponse(i);
            return new Gson().toJson(respuesta);
        }

    }

    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Parametro obj) { // nicolino, 1234
        Respuesta respuesta = new Respuesta();

        FactoryDao factory = FactoryDao.getFactoryInstance();
        ParametroDao dao = factory.getNewParametroDao();
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
    
    
    /*  Ver notas   */
    @Path("/showTable")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getByGroupShowTable(Parametro param) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        ParametroDao dao = factory.getNewParametroDao();

        try {
            ArrayList<Parametro> parametro = dao.getByGrupoTable(param);

            respuesta.setSuccess(true);
            respuesta.setMessage("lista obtenida");
            respuesta.setResponse(parametro);
            
            return new Gson().toJson(respuesta);
        } catch (Exception ex) {
            respuesta.setMessage(ex.getMessage());
            return new Gson().toJson(respuesta);
        }
    }
}
