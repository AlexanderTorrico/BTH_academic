/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Parametro;
import java.util.ArrayList;

/**
 *
 * @author Derxal
 */
public abstract class ParametroDao {
    
    public abstract int insert (Parametro obj) throws Exception;
    public abstract ArrayList<Parametro> getByGrupo(int id) throws Exception;
    public abstract ArrayList<Parametro> getList() throws Exception;
}
