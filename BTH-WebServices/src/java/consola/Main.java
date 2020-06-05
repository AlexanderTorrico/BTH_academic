/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

import dao.ColegioDao;
import dao.PagoDao;
import dto.Colegio;
import dto.Pago;
import dto.Search;
import factory.FactoryDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josec
 */
public class Main {

    public static void main(String[] args) {
        FactoryDao factory = FactoryDao.getFactoryInstance();
        PagoDao dao = factory.getNewPagoDao();

        // Listar datos de una tabla
//        List<Genero> generos = dao.getList();
//        for (Genero genero : generos) {
//            System.out.println(genero.getNombreGenero());
//        }
//        
//         Insertando un registro a la tabla
//        Pago objColegio = new Pago();
//        objColegio.setMonto(15.6);
//        objColegio.setMes(8);
//        objColegio.setFecha("20260324");
//        objColegio.setIdEstudiantes_grupos(1);
//        
//        int generoId = 0;
//        try {
//            generoId = dao.insert(objColegio);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        
        // Obteniendo un registro de la base de datos
        Search objCol = new Search();
        objCol = dao.getEstudent("110");
        System.out.println(objCol.getCarrera());
        
//        objGenero.setNombreGenero("Musica Romantica");
//        
//        try {
//            // Modificando un registro de la base de datos
//            dao.update(objGenero);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        // Eliminando un registro de la base de datos
        //dao.delete(generoId);
    }

}
