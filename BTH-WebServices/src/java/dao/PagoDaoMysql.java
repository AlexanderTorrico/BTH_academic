/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
import dto.MovilTrimestre;
import dto.Pago;
import dto.Parametro;
import dto.User;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author merxdev
 */
public class PagoDaoMysql extends PagoDao{

    @Override
    public ArrayList<MovilTrimestre> get(User param) throws Exception {
        ArrayList<MovilTrimestre> lista = new ArrayList<MovilTrimestre>();

        String query = "SELECT p.mes, p.monto, p.fecha from (select * from tblEstudiantes_grupos " +
"                                    where idEstudiante = "+param.getId()+" and idGrupo = "+param.getIdGrupo()+") ec " +
"                                join tblPagos p on p.idEstudiantes_grupos = ec.id";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                MovilTrimestre obj = new MovilTrimestre();

                obj.setMes(objResultSet.getInt("mes")+"");

                obj.setMonto(objResultSet.getDouble("monto")+"");
                
                obj.setFecha("2020-03-24");

                lista.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage()+" " + query);
        }
        return lista;
    }
    
}
