/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Nota;
import dto.Parametro;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public abstract class NotaDao {
    
    public abstract ArrayList<Nota> get(Parametro param) throws Exception;
    public abstract int update(Nota param) throws Exception;
}
