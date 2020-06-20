/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Asistencia;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public abstract class AsistenciaDao  {
    public abstract ArrayList<Asistencia> getDateByGroup(int id) throws Exception;
    public abstract ArrayList<Asistencia> getlistAssistance(Asistencia obj) throws Exception;
    
    public abstract int insertAsistenciaByGroup(Asistencia obj) throws Exception;
    public abstract int updateAssistance(Asistencia obj) throws Exception;
    
    public abstract ArrayList<Asistencia> listAssistanceCount(Asistencia obj) throws Exception;
    
}
