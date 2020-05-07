/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Movil;
import dto.User;

/**
 *
 * @author merxdev
 */
public abstract class MovilInfoEstudianteDao {
    
    public abstract Movil get(Movil obj) throws Exception;
}
