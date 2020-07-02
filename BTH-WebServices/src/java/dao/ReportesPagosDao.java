/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ReportesPagos;
import java.util.ArrayList;

/**
 *
 * @author LEO
 */
public abstract class ReportesPagosDao {
    
    
    
    public abstract ArrayList<ReportesPagos> getList(ReportesPagos objReporte);

}
