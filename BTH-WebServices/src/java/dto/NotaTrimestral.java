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
public class NotaTrimestral {
    private int id;
    private double primer, segundo, tercer;
    private String nombre;

    public NotaTrimestral() {
    }

    public NotaTrimestral(int id, double primer, double segundo, double tercer) {
        this.id = id;
        this.primer = primer;
        this.segundo = segundo;
        this.tercer = tercer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrimer() {
        return primer;
    }

    public void setPrimer(double primer) {
        this.primer = primer;
    }

    public double getSegundo() {
        return segundo;
    }

    public void setSegundo(double segundo) {
        this.segundo = segundo;
    }

    public double getTercer() {
        return tercer;
    }

    public void setTercer(double tercer) {
        this.tercer = tercer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
