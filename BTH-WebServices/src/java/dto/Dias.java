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
public class Dias {

    private int Id;
    private int Dia;
    private int IdGrupo;
    private int IdHora;
    private String Hora;

    public Dias() {
    }

    public Dias(int Id, int Dia, int IdGrupo, int IdHora, String Hora) {
        this.Id = Id;
        this.Dia = Dia;
        this.IdGrupo = IdGrupo;
        this.IdHora = IdHora;
        this.Hora = Hora;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getDia() {
        return Dia;
    }

    public void setDia(int Dia) {
        this.Dia = Dia;
    }

    public int getIdGrupo() {
        return IdGrupo;
    }

    public void setIdGrupo(int IdGrupo) {
        this.IdGrupo = IdGrupo;
    }

    public int getIdHora() {
        return IdHora;
    }

    public void setIdHora(int IdHora) {
        this.IdHora = IdHora;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

}
