/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author merxdev
 */
public class Movil {
    
    private String id, idColegio;
    private String ci, nombre, aPaterno, aMaterno, idEG, deColegio, idGestion, nivel, aColegio, docente, carrera;
    private String password;

    public Movil() {
    }

    public Movil(String id, String idColegio, String ci, String nombre, String aPaterno, String aMaterno, String idEG, String deColegio, String idGestion, String nivel, String aColegio, String docente, String carrera, String password) {
        this.id = id;
        this.idColegio = idColegio;
        this.ci = ci;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.idEG = idEG;
        this.deColegio = deColegio;
        this.idGestion = idGestion;
        this.nivel = nivel;
        this.aColegio = aColegio;
        this.docente = docente;
        this.carrera = carrera;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(String idColegio) {
        this.idColegio = idColegio;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getIdEG() {
        return idEG;
    }

    public void setIdEG(String idEG) {
        this.idEG = idEG;
    }

    public String getDeColegio() {
        return deColegio;
    }

    public void setDeColegio(String deColegio) {
        this.deColegio = deColegio;
    }

    public String getIdGestion() {
        return idGestion;
    }

    public void setIdGestion(String idGestion) {
        this.idGestion = idGestion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getaColegio() {
        return aColegio;
    }

    public void setaColegio(String aColegio) {
        this.aColegio = aColegio;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
