
package services;

import dto.Permiso;
import com.google.gson.Gson;
import dao.PermisoDao;
import factory.FactoryDao;
import java.util.ArrayList;
import javax.ws.rs.GET;
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
    public String RolPermisos() {
        Respuesta respuesta = new Respuesta();
        FactoryDao factory = FactoryDao.getFactoryInstance();
        PermisoDao dao = factory.getNewPermisoDao();
        ArrayList<Permiso> rol = dao.getList();
        respuesta.setSuccess(true);
        respuesta.setMessage("Lista de Permisos");
        respuesta.setResponse(rol);
        return new Gson().toJson(respuesta);
    }
    
}
