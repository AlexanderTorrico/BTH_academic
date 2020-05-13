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
public class Nota {
    private int id, idEstudiante_grupo, idParametro_grupo;
    private double nota;
    private String nombre, parametro;

    public Nota() {
    }

    public Nota(int id, int idEstudiante_grupo, int idParametro_grupo, double nota) {
        this.id = id;
        this.idEstudiante_grupo = idEstudiante_grupo;
        this.idParametro_grupo = idParametro_grupo;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiante_grupo() {
        return idEstudiante_grupo;
    }

    public void setIdEstudiante_grupo(int idEstudiante_grupo) {
        this.idEstudiante_grupo = idEstudiante_grupo;
    }

    public int getIdParametro_grupo() {
        return idParametro_grupo;
    }

    public void setIdParametro_grupo(int idParametro_grupo) {
        this.idParametro_grupo = idParametro_grupo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
    
    
}
