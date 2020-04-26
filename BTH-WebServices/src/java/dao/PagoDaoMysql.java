/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.Conexion;
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
    public ArrayList<Pago> get(User param) throws Exception {
        ArrayList<Pago> lista = new ArrayList<Pago>();

        String query = "SELECT p.mes, p.monto, p.fecha from (select * from tblEstudiantes_grupos " +
"                                    where idEstudiante = 1 and id = 1) ec " +
"                                join tblPagos p on p.idEstudiantes_grupos = ec.id";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Pago obj = new Pago();

                obj.setMes(objResultSet.getInt("mes"));

                obj.setMonto(objResultSet.getDouble("monto"));

                lista.add(obj);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage()+" " + query);
        }
        return lista;
    }
    
}
