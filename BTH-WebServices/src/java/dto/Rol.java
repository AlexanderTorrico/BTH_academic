
package dto;

/**
 *
 * @author Carlos Clavijo
 */
public class Rol {
    
    private int id;
    private String nombre;
    
    public Rol() {
        
    }
    
    public Rol (int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
