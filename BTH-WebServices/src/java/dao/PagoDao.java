/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MovilTrimestre;
import dto.Pago;
import dto.User;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public abstract class PagoDao {
    
    public abstract ArrayList<MovilTrimestre> get(User param) throws Exception;
}
