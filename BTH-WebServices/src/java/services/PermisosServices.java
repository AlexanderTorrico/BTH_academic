
package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Carlos Clavijo
 */
@Path("Permiso")
public class PermisosServices{
    
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String UsuarioRol() {
        return null;
    }
    
}