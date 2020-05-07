/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Carrera;
import dto.InformacionesCarrera;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis
 */
public abstract class indexInformacionDao {
    public abstract ArrayList<Carrera> getListIndex();
    public abstract ArrayList<InformacionesCarrera> getListInformacion(int id);

}
