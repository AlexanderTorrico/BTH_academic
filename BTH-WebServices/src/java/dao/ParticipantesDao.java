/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Participantes;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public abstract class ParticipantesDao {
    
    public abstract void insert(String obj)throws Exception;
    
    public abstract ArrayList<Participantes> listByProyect(Participantes obj)throws Exception;
    
}
