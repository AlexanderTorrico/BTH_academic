/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author merxdev
 */
public class Asistencia {
    int id, idEstudiantes_grupos;
    String tipo;
    String motivo, trimestre;
    Date fecha;
    int presente, falta, retraso;
    String nombre, colegio;

    public Asistencia() {
    }

    public Asistencia(int id, int idEstudiantes_grupos, String tipo, String motivo, String trimestre, Date fecha, String nombre, String colegio) {
        this.id = id;
        this.idEstudiantes_grupos = idEstudiantes_grupos;
        this.tipo = tipo;
        this.motivo = motivo;
        this.trimestre = trimestre;
        this.fecha = fecha;
        this.nombre = nombre;
        this.colegio = colegio;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiantes_grupos() {
        return idEstudiantes_grupos;
    }

    public void setIdEstudiantes_grupos(int idEstudiantes_grupos) {
        this.idEstudiantes_grupos = idEstudiantes_grupos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public int getPresente() {
        return presente;
    }

    public void setPresente(int presente) {
        this.presente = presente;
    }

    public int getFalta() {
        return falta;
    }

    public void setFalta(int falta) {
        this.falta = falta;
    }

    public int getRetraso() {
        return retraso;
    }

    public void setRetraso(int retraso) {
        this.retraso = retraso;
    }
    
    
    
    
}
