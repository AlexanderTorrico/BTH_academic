/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;




/**
 *
 * @author LEO
 */
public class Grupos {

    private int Id;
    private String Nivel;
    private String Estado;
    private int IdGestion;
    private int IdColegio;
    private int IdCarrera;
    private int IdDocente;
    private String Gestion;
    private String Colegio;
    private String Carrera;
    private String Docente;
    private double Costo;
    private Date FechaInicio;
    private Date FechaFin;

    public Grupos() {

    }

    public int getIdGestion() {
        return IdGestion;
    }

    public void setIdGestion(int IdGestion) {
        this.IdGestion = IdGestion;
    }

    public int getIdColegio() {
        return IdColegio;
    }

    public void setIdColegio(int IdColegio) {
        this.IdColegio = IdColegio;
    }

    public int getIdCarrera() {
        return IdCarrera;
    }

    public void setIdCarrera(int IdCarrera) {
        this.IdCarrera = IdCarrera;
    }

    public int getIdDocente() {
        return IdDocente;
    }

    public void setIdDocente(int IdDocente) {
        this.IdDocente = IdDocente;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getGestion() {
        return Gestion;
    }

    public void setGestion(String Gestion) {
        this.Gestion = Gestion;
    }

    public String getColegio() {
        return Colegio;
    }

    public void setColegio(String Colegio) {
        this.Colegio = Colegio;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public String getDocente() {
        return Docente;
    }

    public void setDocente(String Docente) {
        this.Docente = Docente;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }
   
  
}
