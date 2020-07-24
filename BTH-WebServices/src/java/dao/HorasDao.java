/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Horas;
import java.util.ArrayList;

/**
 *
 * @author LEO
 */
public abstract class HorasDao {

    public abstract int insert(Horas obj) throws Exception;

    public abstract int update(Horas obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Horas> getList();

    public abstract Horas get(int id);
}
