/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import dto.MovilTrimestre;
import dto.Pago;
import dto.PlanPagos;
import dto.Search;
import dto.User;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public abstract class PagoDao {

    public abstract ArrayList<MovilTrimestre> get(User param) throws Exception;

    public abstract ArrayList<Pago> getMesFaltanteAPagar(Pago param) throws Exception;

    public abstract int insert(Pago obj) throws Exception;
    
    public abstract Search getEstudent(String ci);
    
    public abstract ArrayList<PlanPagos> getPlanPagos(String ci) throws Exception;
}
