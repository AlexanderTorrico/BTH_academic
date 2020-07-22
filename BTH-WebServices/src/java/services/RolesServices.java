
package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dao.UsuarioDao;

/**
 *
 * @author Carlos Clavijo
 */
@Path("Rol")
public class RolesServices {
    
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String UsuarioRol() {
        UsuarioDao a;
        return null;
    }
    
}