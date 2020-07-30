package services;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dao.UsuarioDao;
import dao.RolDao;
import dao.UserRolesDao;
import dto.Usuario;
import dto.UserRoles;
import dto.Rol;
import factory.FactoryDao;
import java.util.ArrayList;

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

    @Path("/Asignar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String asignarRol(Usuario user) {
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

    @Path("/ListadoRoles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String Roles() {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        RolDao dao = factory.getNewRolDao();
        ArrayList<Rol> rol = dao.getList();
        respuesta.setSuccess(true);
        respuesta.setMessage("Lista de Rol");
        respuesta.setResponse(rol);
        return new Gson().toJson(respuesta);
    }
    
    @Path("/InsertarRol")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String insertarRol(Rol nuevoRol) throws Exception {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        RolDao dao = factory.getNewRolDao();
        Rol rol= new Rol();
        rol.setNombre(nuevoRol.getNombre());
        int i = dao.insert(rol);
        if (i == 0) {
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo añadir el rol");
            respuesta.setResponse(null);
            return new Gson().toJson(respuesta);
        }
        respuesta.setSuccess(true);
        respuesta.setMessage("Sí se pudo añadir el rol");
        respuesta.setResponse(null);
        return new Gson().toJson(respuesta);
    }
    
    @Path("/AsignarColegio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String asignarColegio(UserRoles userRoles) throws Exception {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        RolDao dao = factory.getNewRolDao();
        UserRoles uRoles = new UserRoles();
        uRoles.setIdReferencia(userRoles.getIdRol());
        uRoles.setIdUsuario(userRoles.getIdUsuario());
        System.out.println(userRoles.toString());
        System.out.println("ID REFERENCIA = " + uRoles.getIdRol());
        System.out.println("ID USUARIO = " + uRoles.getIdUsuario());
        int i = dao.asignarColegio(uRoles);
        if (i == 0) {
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo asignar el colegio");
            respuesta.setResponse(null);
            return new Gson().toJson(respuesta);
        }
        respuesta.setSuccess(true);
        respuesta.setMessage("Sí se pudo asignar el colegio");
        respuesta.setResponse(null);
        return new Gson().toJson(respuesta);
    }
    
}