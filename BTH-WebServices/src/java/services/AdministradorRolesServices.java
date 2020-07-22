package services;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dao.UsuarioDao;
import dto.Usuario;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.DELETE;

/**
 *
 * @author Carlos Clavijo
 */
@Path("Usuario")
public class AdministradorRolesServices {

    @Path("/Listado")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String UsuarioRol() {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        UsuarioDao dao = factory.getNewUsuarioDao();
        ArrayList<Usuario> usuario = dao.getList();
        respuesta.setSuccess(true);
        respuesta.setMessage("Lista de Usuarios");
        respuesta.setResponse(usuario);
        return new Gson().toJson(respuesta);
    }

    @Path("/Eliminar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminarRol(Usuario user) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        UsuarioDao dao = factory.getNewUsuarioDao();
        int i = dao.eliminarUsuarioRol(user.getNombre(), user.getId());
        if (i == 0) {
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo quitar el Rol asignado");
            respuesta.setResponse(null);
            return new Gson().toJson(respuesta);
        }
        respuesta.setSuccess(true);
        respuesta.setMessage("Sí se pudo quitar el Rol asignado");
        respuesta.setResponse(null);
        return new Gson().toJson(respuesta);
    }
    
    @Path("/Insertar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String insertarRol(Usuario user) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        UsuarioDao dao = factory.getNewUsuarioDao();
        int i = dao.insertarUsuarioRol(user.getNombre(), user.getId());
        if (i == 0) {
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo asignar el rol");
            respuesta.setResponse(null);
            return new Gson().toJson(respuesta);
        }
        respuesta.setSuccess(true);
        respuesta.setMessage("Sí se pudo asignar el Rol");
        respuesta.setResponse(null);
        return new Gson().toJson(respuesta);
    }

}
