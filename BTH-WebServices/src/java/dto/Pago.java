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
public class Pago {
    private int id, idEstudiantes_grupos;
    private double monto;
    private int mes;
    private String nombre;

    public Pago() {
    }

    public Pago(int id, int idEstudiantes_grupos, double monto, int mes) {
        this.id = id;
        this.idEstudiantes_grupos = idEstudiantes_grupos;
        this.monto = monto;
        this.mes = mes;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
