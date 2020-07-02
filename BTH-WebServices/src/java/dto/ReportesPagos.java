/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author LEO
 */
public class ReportesPagos {

    private int IdGrupo;
    private String Estado;
    private int IdColegio;
    private String NombreColegio;
    private int IdCarrera;
    private String NombreCarrera;
    private double Costo;
    private String Fecha;
    
    public ReportesPagos() {
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getIdGrupo() {
        return IdGrupo;
    }

    public void setIdGrupo(int IdGrupo) {
        this.IdGrupo = IdGrupo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getIdColegio() {
        return IdColegio;
    }

    public void setIdColegio(int IdColegio) {
        this.IdColegio = IdColegio;
    }

    public String getNombreColegio() {
        return NombreColegio;
    }

    public void setNombreColegio(String NombreColegio) {
        this.NombreColegio = NombreColegio;
    }

    public int getIdCarrera() {
        return IdCarrera;
    }

    public void setIdCarrera(int IdCarrera) {
        this.IdCarrera = IdCarrera;
    }

    public String getNombreCarrera() {
        return NombreCarrera;
    }

    public void setNombreCarrera(String NombreCarrera) {
        this.NombreCarrera = NombreCarrera;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }
    
    

}
