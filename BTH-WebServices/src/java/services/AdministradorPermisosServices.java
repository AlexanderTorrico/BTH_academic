package services;

import dto.Permiso;
import com.google.gson.Gson;
import dao.PermisoDao;
import dao.RolDao;
import dto.RolPermiso;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Carlos Clavijo
 */
@Path("Permisos")
public class AdministradorPermisosServices {

    @Path("/Listado")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String Permisos() {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        PermisoDao dao = factory.getNewPermisoDao();
        ArrayList<Permiso> permiso = dao.getList();
        respuesta.setSuccess(true);
        respuesta.setMessage("Lista de Permisos");
        respuesta.setResponse(permiso);
        return new Gson().toJson(respuesta);
    }

    @Path("/Asignar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String asignarPermiso(Permiso permiso) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        RolDao dao = factory.getNewRolDao();
        int i = dao.insertarRoPermiso(permiso.getNombre(), permiso.getTipo(), permiso.getIdRol());
        if (i == 0) {
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo asignar el Permiso");
            respuesta.setResponse(permiso);
            return new Gson().toJson(respuesta);
        }
        respuesta.setSuccess(true);
        respuesta.setMessage("Sí se pudo asignar el Permiso");
        respuesta.setResponse(permiso);
        return new Gson().toJson(respuesta);
    }
    
    @Path("/Designar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String designarPermiso(Permiso permiso) {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        RolDao dao = factory.getNewRolDao();
        int i = dao.eliminarRolPermiso(permiso.getNombre(), permiso.getTipo(), permiso.getIdRol());
        if (i == 0) {
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo designar el Permiso");
            respuesta.setResponse(permiso);
            return new Gson().toJson(respuesta);
        }
        respuesta.setSuccess(true);
        respuesta.setMessage("Sí se pudo designar el Permiso");
        respuesta.setResponse(permiso);
        return new Gson().toJson(respuesta);
    }
    
    @Path("/RolPermisos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String RolPermisos() {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        RolDao dao = factory.getNewRolDao();
        ArrayList<RolPermiso> permiso = dao.getListRolPermisos();
        respuesta.setSuccess(true);
        respuesta.setMessage("Lista de Roles/Permisos");
        respuesta.setResponse(permiso);
        return new Gson().toJson(respuesta);
    }

}
