/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Time;

/**
 *
 * @author LEO
 */
public class Horas {

    private int Id;
    private Time Inicio;
    private Time Fin;

    public Horas() {
    }

    public Horas(int Id, Time Inicio, Time Fin) {
        this.Id = Id;
        this.Inicio = Inicio;
        this.Fin = Fin;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Time getInicio() {
        return Inicio;
    }

    public void setInicio(Time Inicio) {
        this.Inicio = Inicio;
    }

    public Time getFin() {
        return Fin;
    }

    public void setFin(Time Fin) {
        this.Fin = Fin;
    }

}
