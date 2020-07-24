/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Dias;
import java.util.ArrayList;

/**
 *
 * @author LEO
 */
public abstract class DiasDao {

    public abstract int insert(Dias obj) throws Exception;

    public abstract int update(Dias obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Dias> getList();

    public abstract Dias get(int id);

}
