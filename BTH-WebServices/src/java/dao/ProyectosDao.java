/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Carrera;
import dto.InformacionesCarrera;
import dto.Proyectos;
import java.util.ArrayList;

/**
 *
 * @author carlos clavijo
 */
public abstract class ProyectosDao {

    public abstract ArrayList<Proyectos> getList();

    public abstract ArrayList<Proyectos> getListPorCarrera(int idCarrera);

    public abstract int delete(int id);

    public abstract int insert(Proyectos proyecto);

    public abstract int update(Proyectos proyecto);
    public abstract Proyectos get(int id);

}
