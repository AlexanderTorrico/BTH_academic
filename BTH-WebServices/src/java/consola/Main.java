/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

import dao.ColegioDao;
import dto.Colegio;
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
        ColegioDao dao = factory.getNewColegioDao();

        // Listar datos de una tabla
//        List<Genero> generos = dao.getList();
//        for (Genero genero : generos) {
//            System.out.println(genero.getNombreGenero());
//        }
//        
//         Insertando un registro a la tabla
//        Colegio objColegio = new Colegio();
//        objColegio.setNombre("MARCELO1");
//        objColegio.setSigep("ASD");
//        objColegio.setDirector("asdasd");
//        objColegio.setDireccion("asd");
//        objColegio.setTelefono("32132");
//        objColegio.setEsModulo(1);
//        int generoId = 0;
//        try {
//            generoId = dao.insert(objColegio);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        
        // Obteniendo un registro de la base de datos
//        Colegio objCol = new Colegio();
//        objCol = dao.get(generoId);
//        System.out.println(objCol.toString());
        
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
