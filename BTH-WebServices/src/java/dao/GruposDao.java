/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Grupos;
import java.util.ArrayList;

/**
 *
 * @author LEO
 */
public abstract class GruposDao {

    public abstract int insert(Grupos obj) throws Exception;

    public abstract int update(Grupos obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Grupos> getList();

    public abstract Grupos get(int id);

}
