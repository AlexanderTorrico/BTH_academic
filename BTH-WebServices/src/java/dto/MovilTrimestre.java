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
public class MovilTrimestre {
    private String id, idEstudiantes_grupos;
    private String monto;
    private String mes;
    private String fecha;

    public MovilTrimestre() {
    }

    public MovilTrimestre(String id, String idEstudiantes_grupos, String monto, String mes, String fecha) {
        this.id = id;
        this.idEstudiantes_grupos = idEstudiantes_grupos;
        this.monto = monto;
        this.mes = mes;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEstudiantes_grupos() {
        return idEstudiantes_grupos;
    }

    public void setIdEstudiantes_grupos(String idEstudiantes_grupos) {
        this.idEstudiantes_grupos = idEstudiantes_grupos;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
}
