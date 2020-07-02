/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import dto.Gestion;
import java.util.ArrayList;

/**
 *
 * @author LEO
 */
public abstract class GestionDao {
    
    public abstract int insert(Gestion obj) throws Exception;

    public abstract int update(Gestion obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Gestion> getList();

    public abstract Gestion get(int id);

}
