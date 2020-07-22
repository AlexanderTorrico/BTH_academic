package dto;

import java.util.ArrayList;

/**
 *
 * @author Carlos Clavijo
 */
public class Usuario {

    private int id;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String email;
    private String password;
    private int estado;
    private ArrayList<Rol> roles = new ArrayList<Rol>();

    public Usuario() {

    }

    public Usuario(int id, String nombre, String apPaterno, String apMaterno, String email, String password, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.email = email;
        this.password = password;
        this.estado = estado;
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

    public String getApPaterno() {
        return this.apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return this.apPaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public ArrayList<Rol> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Rol> roles) {
        this.roles = roles;
    }
    
    public void addRol(Rol rol) {
        this.roles.add(rol);
    }

}
